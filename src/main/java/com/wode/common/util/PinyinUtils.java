package com.wode.common.util;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * @author mengkaixuan
 *
 */
public class PinyinUtils {
	
	private static final String pinyinHolder = "";

	/**
	 * 获得短拼音
	 * 
	 * @param str
	 *            aaa我知道了bbb<br/>
	 *            <p>
	 * @return aaawzdlbbb
	 */
	public static String getShortPinyin(String str) {
		if (StringUtils.isBlank(str)) {
			return pinyinHolder;
		}
		HanyuPinyinOutputFormat output = new HanyuPinyinOutputFormat();
		output.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		output.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		output.setVCharType(HanyuPinyinVCharType.WITH_V);
		char[] cs = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cs.length; i++) {
			try {
				if (cs[i] > 0 && cs[i] < 255) {
					sb.append(cs[i]);
					continue;
				}
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(
						cs[i], output);
				if (pinyinArray != null) {
					sb.append(pinyinArray[0].charAt(0));
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {

			}
		}
		return sb.toString();
	}

	/**
	 * 获得长拼音
	 * <p>
	 * aaa我知道了bbb
	 * </p>
	 * <p>
	 * aaawozhidaolebbb
	 * </p>
	 * 
	 * @param str
	 * @return aaawozhidaolebbb
	 */
	public static String getPinyin(String str) {
		if (StringUtils.isBlank(str)) {
			return pinyinHolder;
		}
		HanyuPinyinOutputFormat output = new HanyuPinyinOutputFormat();
		output.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		try {
			return PinyinHelper.toHanyuPinyinString(str, output, pinyinHolder);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			return "";
		}
	}
	
	public static void main(String[] args) {
		getPinyin("");
	}
}
