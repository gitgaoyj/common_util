package com.wode.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ObjectUtils {
	/**
	 * 判断t的值如果是null 返回 t1 否则返回t 目的：解决很多的if判断，导致代码观察性低
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static <T> T returnIsNotNullT(T t, T t1) {
		return null == t ? t1 : t;
	}

	public static boolean classOfSrc(Object source, Object target,List<String> exclude) {
		boolean rv = false;
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			String nameKey = field.getName();
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap) target;
				String srcValue = getClassValue(source, nameKey) == null ? ""
						: getClassValue(source, nameKey).toString();
				if (tarMap.get(nameKey) == null) {
					rv = true;
					break;
				}
				if (!tarMap.get(nameKey).equals(srcValue)) {
					rv = true;
					break;
				}
			} else {
				if(exclude(exclude, nameKey)) {
					continue;
				}
				String srcValue = getClassValue(source, nameKey) == null ? ""
						: getClassValue(source, nameKey).toString();
				String tarValue = getClassValue(target, nameKey) == null ? ""
						: getClassValue(target, nameKey).toString();
				if (!srcValue.equals(tarValue)) {
					rv = true;
					break;
				}
			}
		}
		return rv;
	}

	/**
	 * 根据字段名称取值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getClassValue(Object obj, String fieldName) {
		if (obj == null) {
			return null;
		}
		try {
			Class beanClass = obj.getClass();
			Method[] ms = beanClass.getMethods();
			for (int i = 0; i < ms.length; i++) {
				// 非get方法不取
				if (!ms[i].getName().startsWith("get")) {
					continue;
				}
				Object objValue = null;
				try {
					objValue = ms[i].invoke(obj, new Object[] {});
				} catch (Exception e) {
					continue;
				}
				if (objValue == null) {
					continue;
				}
				if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
						|| ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
					return objValue;
				} else if (fieldName.toUpperCase().equals("SID") && (ms[i].getName().toUpperCase().equals("ID")
						|| ms[i].getName().substring(3).toUpperCase().equals("ID"))) {
					return objValue;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 排除不需要比对的属性名字
	 * @param exclude
	 * @return
	 */
	private static boolean exclude(List<String> exclude,String name) {
		if(null == exclude) {
			return false;
		}
		if(exclude.indexOf(name) == -1) {
			return false;
		}
		return true;
	}
}
