package com.wode.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie操作工具类
 * 
 * @author 谷子夜
 *
 */
public class CookiesUtil {

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}
	
	  public static String getCookie(HttpServletRequest request, String name)
	  {
	    Cookie[] arrayOfCookie1 = request.getCookies();
	    if (arrayOfCookie1 != null)
	      try
	      {
	        name = URLEncoder.encode(name, "UTF-8");
	        for (Cookie localCookie : arrayOfCookie1)
	          if (name.equals(localCookie.getName()))
	            return URLDecoder.decode(localCookie.getValue(), "UTF-8");
	      }
	      catch (UnsupportedEncodingException localUnsupportedEncodingException1)
	      {
	        localUnsupportedEncodingException1.printStackTrace();
	      }
	    return null;
	  }

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 添加cookie
	 */
	public static void addCookie(HttpServletResponse response, String name,String value, Integer maxAge, String path) {
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie localCookie = new Cookie(name, value);
			if (maxAge != null)
				localCookie.setMaxAge(maxAge.intValue());
			if (StringUtils.isNotEmpty(path))
				localCookie.setPath(path);
			response.addCookie(localCookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
