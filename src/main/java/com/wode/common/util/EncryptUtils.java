package com.wode.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

import sun.misc.BASE64Decoder;

public class EncryptUtils {



	private static final String DEFAULT_SALT = "$1$KTCBSKYC$";
	/**
	 * php cripty function = java passwordCrypt
	 * use it to encode user password
	 * @param user_password
	 * @param salt can be null or empty
	 * @return encode string
	 */
	public static String Md5Encode(String user_password,String salt){
		if(StringUtils.isNullOrEmpty(salt)){
			salt = DEFAULT_SALT;
		}
		String enStr;
		try {
			DigestUtils.getDigest("MD5");
			enStr = Md5Crypt.md5Crypt(user_password.getBytes("UTF-8"), salt);
			return enStr.substring(salt.length(), enStr.length());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
				
	}

	public static String Md5(String user_password,String salt){
		if(StringUtils.isNullOrEmpty(salt)){
			salt = DEFAULT_SALT;
		}
		String enStr;
			enStr = DigestUtils.md5Hex(user_password+salt);
			return enStr.substring(salt.length(), enStr.length());
	}

	public static String Md5Encode(String user_password){
		return Md5Encode(user_password,null);
	}
	
	/**
	 *  base encode
	 * @param s
	 * @return
	 */
	public static String BASE64Encoder(byte[] s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s);
	}

	/**
	 * base64 decode
	 * @param s
	 * @return
	 */
	public static byte[] BASE64Decode(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args){
		System.out.println(EncryptUtils.Md5Encode("admin123", null));
		String x = EncryptUtils.Md5("admin123", null);
		System.out.println(x);
		x = EncryptUtils.decrypt(x, "haha");
		System.out.println(x);
	}
	
	/**
	 * return base64 code
	 * @param content
	 * @param password
	 * @return
	 */
	public static String encrypt(String content, String password) {
		return BASE64Encoder(EncryptUtils.encryptToByte(content, password));
	}
	/**
	 * 
	 * aes encrypt
	 * @param content
	 *            need to encrypt
	 * @param password
	 *            encrypt password
	 * @return
	 */
	public static byte[] encryptToByte(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(password.getBytes("UTF-8"));
			kgen.init(128, sr);
			
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// ����������
			byte[] byteContent = content.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(byteContent);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * content is base64 code
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(String content, String password) {
		return new String(decryptToByte(BASE64Decode(content),password));
	}
	
	/**
	 * aes decode
	 * 
	 * @param content
	 *            need to decode string
	 * @param password
	 *            decode password
	 * @return
	 */
	public static byte[] decryptToByte(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(password.getBytes("UTF-8"));
			kgen.init(128, sr);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}