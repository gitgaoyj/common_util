package com.wode.common.util;

import java.math.BigDecimal;

/**
 * User: fdxu Date: 12-8-22
 */
public class NumberUtil {

	/**
	 * 获取Long型
	 * @param s
	 * @return
	 */
    public static Long toLong(Object s) {
    	if(s==null) {
    		return null;
    	} else if(s instanceof Long) {
    		return (Long)s;
    	} else if (s instanceof Integer) {
    		return ((Integer)s) + 0L;
    	} else if (s instanceof Double) {
    		return ((Double)s).longValue();
    	} else if (s instanceof BigDecimal) {
    		return ((BigDecimal)s).longValue();
    	} else if (s instanceof String) {
    		String str = (String) s;
    		if(str.contains(".")) {
    			str.substring(0, str.indexOf("."));
    		}
    		if("".equals(str)) {
    			return null;
    		} else {
    			return Long.valueOf((String)str);
    		}
    	} else {
    		return Long.valueOf(s.toString());
    	}
    }
    
    /**
	 * 获取Long型
	 * @param s
	 * @return
	 */
    public static Double toDouble(Object s) {
    	if(s==null) {
    		return null;
    	} else if(s instanceof Long) {
    		return ((Long)s).doubleValue();
    	} else if (s instanceof Integer) {
    		return ((Integer)s).doubleValue();
    	} else if (s instanceof Double) {
    		return (Double)s;
    	} else if (s instanceof BigDecimal) {
    		return ((BigDecimal)s).doubleValue();
    	} else if (s instanceof String) {
    		if("".equals(s)) {
    			return null;
    		} else {
    			return Double.valueOf((String)s);
    		}
    	} else {
    		return Double.valueOf(s.toString());
    	}
    }
    
    /**
	 * 获取Long型
	 * @param s
	 * @return
	 */
    public static BigDecimal toBigDecimal(Object s) {
    	if(s==null) {
    		return null;
    	} else if(s instanceof Long) {
    		return new BigDecimal((Long)s);
    	} else if (s instanceof Integer) {
    		return new BigDecimal((Integer)s);
    	} else if (s instanceof Double) {
    		return new BigDecimal((Double)s);
    	} else if (s instanceof Float) {
    		return new BigDecimal((Float)s);
    	} else if (s instanceof BigDecimal) {
    		return (BigDecimal)s;
    	} else if (s instanceof String) {
    		if("".equals(s)) {
    			return null;
    		} else {
    			return new BigDecimal((String)s);
    		}
    	} else {
    		return new BigDecimal(s.toString());
    	}
    	
    	
    }

    /**
	 * 获取Long型
	 * @param s
	 * @return
	 */
    public static Integer toInteger(Object s) {
    	if(s==null) {
    		return null;
    	} else if(s instanceof Long) {
    		return ((Long)s).intValue();
    	} else if (s instanceof Integer) {
    		return (Integer)s;
    	} else if (s instanceof Double) {
    		return ((Double)s).intValue();
    	} else if (s instanceof BigDecimal) {
    		return ((BigDecimal)s).intValue();
    	} else if (s instanceof String) {
    		String str = (String) s;
    		if(str.contains(".")) {
    			str=str.substring(0, str.indexOf("."));
    		}
    		if("".equals(str)) {
    			return null;
    		} else {
    			return new Integer((String)str);
    		}
    	} else {
    		return new Integer(s.toString());
    	}
    }
    

    /**
	 * 获取Long型
	 * @param s
	 * @return
	 */
    public static boolean equals(Object s,Object t) {
    	if(s==null && t==null) return true;
    	if(s==null || t==null) return false;
    	
    	if(s instanceof Long) {
    		return toLong(s)==toLong(t);
    	} else if (s instanceof Integer) {
    		return toInteger(s).compareTo(toInteger(t))==0;
    	} else if (s instanceof Double) {
    		return toDouble(s).compareTo(toDouble(t))==0;
    	} else if (s instanceof BigDecimal) {
    		return toBigDecimal(s).compareTo(toBigDecimal(t)) ==0;
    	} else {
    		return s.equals(t);
    	}
    }
    /**
	 * 获取Long型
	 * @param s
	 * @return
	 */
    public static int toInt(Object s) {
    	Integer d = toInteger(s);
    	if(d==null) return 0;
    	return d;
    }
    
    public static String removeTailZero(String n) {
    	if(n==null) return n;
    	if("".equals(n)) return n;
    	if(n.length()==1) return n;
    	if(!n.contains(".")) return n;

    	if(n.charAt(n.length()-1) == '0' || n.charAt(n.length()-1) == '.') return removeTailZero(n.substring(0, n.length()-1));
    	return n;
    }
    
    public static boolean isGreaterZero(Object s) {
    	if(s==null) return false;
    	
    	if(s instanceof Long) {
    		return toLong(s)>0;
    	} else if (s instanceof Integer) {
    		return toInteger(s)>0;
    	} else if (s instanceof Double) {
    		return toDouble(s)>0;
    	} else if (s instanceof BigDecimal) {
    		return toBigDecimal(s).compareTo(BigDecimal.ZERO) >0;
    	} else {
    		return false;
    	}
    }
    
	/**
	 * 检查金额是否为空 空返回0
	 * @param BigDecimal
	 * @return
	 */
	public static BigDecimal checkBigDecimal(String bigDecimal){
		if(bigDecimal==null || "".equals(bigDecimal.trim())){
			return BigDecimal.ZERO;
		}else{
			return new BigDecimal(bigDecimal);
		}
	}
}
