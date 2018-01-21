package com.wode.common.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author mengkaixuan
 *
 */
public class StringUtils {

	private static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * http://domain or http://domain/ return http://domain/
	 * 
	 * @param domain
	 * @return
	 */
	public static String domainSplit(String domain) {
		if (isEmpty(domain))
			return "/";
		if (domain.trim().endsWith("/")) {
			domain = domain.trim();
			domain = domain.substring(0, domain.length() - 1);
		}
		domain = domain + "/";
		return domain;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int strlength(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * 
	 * @param origin
	 *            原始字符串
	 * @param len
	 *            截取长度(一个汉字长度按2算的)
	 * @param c
	 *            后缀
	 * @return 返回的字符串
	 */
	public static String tosubstring(String origin, int len, String c) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > strlength(origin)) {
			return origin + c;
		}
		try {
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			origin = "";
			return new String(strByte, 0, len, "GBK") + c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String ListtoString(List<String> list) {
		String s = "";
		for (int i = 0; i < list.size(); i++) {
			String L = list.get(i);
			s = s + "," + L;
		}
		if (list == null || list.size() == 0) {
			return s;
		}
		return s.substring(1);
	}

	public static String getSixLengthFilghNo(String str) {
		String f_no = "";
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				char s = str.charAt(i);
				String[] sd = str.split(String.valueOf(s));
				if (sd[0].length() == 0) {
					return 0 + str;
				} else {
					if (sd[0].length() == 4) {
						f_no = sd[0] + 0 + String.valueOf(s);
						return f_no;
					} else {
						f_no = sd[0] + 0 + String.valueOf(s) + sd[1];
						return f_no;
					}

				}

			}
		}
		return f_no;
	}

	public static int getWordCountRegex(String s) {
		s = s.replaceAll("[^\\x00-\\xff]", "**");
		int length = s.length();
		return length;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * 
	 * @param origin
	 *            原始字符串
	 * @param len
	 *            截取长度(一个汉字长度按2算的)
	 * @param c
	 *            后缀
	 * @return 返回的字符串
	 */
	public static String substring(String origin, int len) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > length(origin)) {
			return origin;
		}
		try {
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			return new String(strByte, 0, len, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private static final String pinyinRegex = "^[A-Za-z]*$";

	/**
	 * 判断是否全是拼音或者字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPinyin(String str) {
		if (null == str) {
			return false;
		}
		Pattern pattern = Pattern.compile(pinyinRegex);
		Matcher matcher = pattern.matcher(str);
		matcher.find();
		return matcher.matches();
	}

	@Deprecated
	public static boolean isNullOrEmpty(String str) {
		if (null == str || "".equals(str))
			return true;
		return false;
	}

	@Deprecated
	public static boolean isNullOrEmpty(Object str) {
		if (null == str || "".equals(str))
			return true;
		return false;
	}

	/**
	 * 比较两个对象内容是否一致
	 * 
	 * @param str
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(Object str, Object str2) {
		if (str == null && str2 == null)
			return true;
		if (str == null && str2 != null)
			return false;
		if (str2 == null && str != null)
			return false;
		if (str.toString().equals(str2.toString()))
			return true;
		return false;
	}

	/**
	 * 去除字符串前后的空格 和逗号 添加人：fdxu
	 * 
	 * @return
	 */
	public static String strTrim(String str) {
		if (str != null && !str.trim().equals("")) {
			if (str.trim().substring(0, 1).equals(",")) {
				str = str.substring(1, str.length());
			}
			if (str.trim().substring(str.length() - 1, str.length()).equals(",")) {
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}

	/**
	 * right trim "trim"
	 * 
	 * @param str
	 * @param trim
	 * @return
	 */
	public static String TrimRight(String str, String trim) {
		if (str != null && !str.trim().equals("")) {
			// if(str.trim().substring(0,1).equals(",")){
			// str = str.substring(1,str.length());
			// }
			if (str.trim().substring(str.length() - trim.length(), str.length()).equals(trim)) {
				str = str.substring(0, str.length() - trim.length());
			}
		}
		return str;
	}

	/**
	 * 判定string的包含关系 str1是否包含str2
	 * 
	 * @return -1 不包含 0 包含并且完全相同 1 包含
	 */
	public static Integer checkStrInclude(String str1, String str2) {
		if (str1 != null && !str1.equals("") && str2 != null && !str2.equals("")) {
			if (str1.equals(str2)) {
				return 0;
			} else {
				if (str1.indexOf(str2) < 0) {
					return str1.indexOf(str2);// return 1;
				} else {
					return 1;
				}
			}
		} else {
			return null;
		}
	}

	/**
	 * 生成数字字母随机字符串
	 */
	public static String randomStr(int count) {
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			buf.append(randString.charAt(random.nextInt(randString.length())));
		}
		return buf.toString();
	}

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	// 判断是否为空�?
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str))
			return true;
		return false;
	}

	/*
	 * @function:判空 @author:
	 */
	public static boolean isEmpty(List list) {
		if (list == null || list.size() == 0)
			return true;
		else
			return false;
	}

	/*
	 * @function:判空 @author:
	 */
	public static boolean isEmpty(Set set) {
		if (set == null || set.size() == 0)
			return true;
		else
			return false;
	}

	/*
	 * @function:判空 @author:
	 */
	public static boolean isEmpty(Map map) {
		if (map == null || map.size() == 0)
			return true;
		else
			return false;
	}

	// 判断是否为空�?
	public static boolean isEmpty(Object Value) {
		if (Value == null)
			return true;
		else
			return false;
	}

	public static boolean isEmpty(StringBuffer value) {
		if (value == null || value.length() <= 0)
			return true;
		else
			return false;
	}

	public static boolean isEmpty(Double value) {
		if (value == null || value.doubleValue() == 0.0)
			return true;
		else
			return false;
	}

	// 判断是否为空�?
	public static boolean isEmpty(Long obj) {
		if (obj == null || obj.longValue() == 0)
			return true;
		else
			return false;
	}

	// 判断是否为空�?
	public static boolean isEmpty(Object[] Value) {
		if (Value == null || Value.length == 0)
			return true;
		else
			return false;
	}

	/**
	 * 判断是不是手机号 ^1[3|4|5|8][0-9]\\d{4,8}$
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		if (!isEmpty(phoneNumber) && phoneNumber.length() == 11 && phoneNumber.matches("^(1[3|4|5|7|8][0-9])\\d{8}$")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断密码
	 */
	public static boolean isPassWord(String pass) {
		if (pass.matches("^[0-9A-Za-z]{4,25}$")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是不是邮箱 ^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$
	 * ^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+$
	 */
	public static boolean isEmail(String email) {
		if (email.endsWith(".com.cn"))
			email = email.substring(0, email.length() - 3);
		if (!isEmpty(email) && email.matches(
				"[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成5位随机数字
	 */
	public static int randomNum() {

		Random random = new Random();

		int num = -1;

		while (true) {

			num = (int) (random.nextDouble() * (100000 - 10000) + 10000);

			if (!(num + "").contains("4"))
				break;

		}

		return num;
	}

	/**
	 * 取字符串中间某一段以其他字符代替
	 * 
	 */
	public static String subString(String oldstr, int begin, int end, String replace) {
		String returnstr;
		if (isEmpty(oldstr) || oldstr.length() < end) {
			returnstr = null;
		} else {
			String a;
			String b;
			a = oldstr.substring(0, begin);
			b = oldstr.substring(end, oldstr.length());
			returnstr = a + replace + b;
		}
		return returnstr;
	}

	public static String toString(Object obj) {
		if (obj == null)
			return null;
		if ("null".equals(obj))
			return null;
		if (obj instanceof String)
			return (String) obj;
		return obj.toString();
	}

	/**
	 * 获取6位随机数字
	 * 
	 */
	public static String getRandomNum() {
		Random random = new Random();
		double num = random.nextDouble();
		String code = "" + num;
		code = code.substring(2, 8);
		return code;
	}

	public static void main(String[] pa) {

		System.out.println(StringUtils.isEmail("15910724591"));
		System.out.println(StringUtils.isEmail("wang@wo-de.com"));
		System.out.println(StringUtils.isEmail("wang@wo.com"));
		System.out.println(StringUtils.isEmail("wang@wo-com"));

	}

	/**
	 * <p>
	 * Checks if the CharSequence contains only Unicode digits. A decimal point is
	 * not a Unicode digit and returns false.
	 * </p>
	 *
	 * <p>
	 * {@code null} will return {@code false}. An empty CharSequence (length()=0)
	 * will return {@code false}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNumeric(null)   = false
	 * StringUtils.isNumeric("")     = false
	 * StringUtils.isNumeric("  ")   = false
	 * StringUtils.isNumeric("123")  = true
	 * StringUtils.isNumeric("12 3") = false
	 * StringUtils.isNumeric("ab2c") = false
	 * StringUtils.isNumeric("12-3") = false
	 * StringUtils.isNumeric("12.3") = false
	 * </pre>
	 *
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if only contains digits, and is non-null
	 * @since 3.0 Changed signature from isNumeric(String) to
	 *        isNumeric(CharSequence)
	 * @since 3.0 Changed "" to return false and not true
	 */
	public static boolean isNumeric(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (cs.charAt(i) == '.')
				continue;
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean toBoolean(Object obj) {
		if (obj == null)
			return false;
		if ("null".equals(obj))
			return false;
		if ("true".equals(obj) || "TRUE".equals(obj) || "1".equals(obj))
			return true;
		return false;
	}
}