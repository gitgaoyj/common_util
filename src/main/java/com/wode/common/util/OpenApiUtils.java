package com.wode.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发平台工具类
 * 
 * @author user
 *
 */
public class OpenApiUtils {

	
	public static void main(String[] args) {
		Map map = new HashMap();
		
		map.put("appid","2214672667838200");
		map.put("method","supplier.products.get");
		map.put("timestamp","2017-06-01 17:36:44");
		//http://localhost:8080/supplier/open?appid=2214672667838200&method=supplier.products.get&timestamp=2017-6-1 17:20:20
		System.out.println(formatParaMap(map, false, false, "jtgf1564"));
	}

	/**
	 *
	 * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
	 * 实现步骤: <br>
	 *
	 * @param paraMap
	 *            要排序的Map对象
	 * @param urlEncode
	 *            是否需要URLENCODE
	 * @param keyToLower
	 *            是否需要将Key转换为全小写 true:key转化成小写，false:不转化
	 * @return
	 */
	public static String formatParaMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower,String secret) {
		String buff = "";
		Map<String, String> tmpMap = paraMap;
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
		// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		// 构造URL 键值对的格式
		StringBuilder buf = new StringBuilder();
		for (Map.Entry<String, String> item : infoIds) {

			String key = item.getKey();
			String val = item.getValue();

			if (urlEncode) {
				try {
					val = new String(val.getBytes(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (keyToLower) {
				buf.append(key.toLowerCase() + val);
			} else {
				buf.append(key + val);
			}
		}
		buff = buf.toString();
		System.out.println(secret + buff + secret);
		return stringMD5(secret + buff + secret);
	}

	/**
	 * 获取加密后的字符串
	 * 
	 * @param input
	 * @return
	 */
	public static String stringMD5(String pw) {
		try {

			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = pw.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}
	
	
	public static int randNum(){
		return  (int)(Math.random()*(9999-1000+1))+1000;//产生1000-9999的随机数;
	}
}
