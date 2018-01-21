package com.wode.common.web;

import static com.wode.common.constant.UserConstant.PASSWORD;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.wode.common.base.CookieUserModel;
import com.wode.common.constant.UserConstant;
import com.wode.common.util.EncryptUtils;
import com.wode.common.util.StringUtils;

public class CookieUtil {

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())){
					//&& request.getServerName().equals(cookies[i].getDomain())) {
				return cookies[i];
			}
		}
		return null;
	}

    public static String getCookieString(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())){
                //&& request.getServerName().equals(cookies[i].getDomain())) {
                return cookies[i].getValue();
            }
        }
        return null;
    }

	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath(getPath(request));
			cookie.setValue("");			
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	/**
	 * 获得cookie user model from session and cookie
	 * @param request
	 * @return
	 */
	public static CookieUserModel getCookieUser(HttpServletRequest request){
		CookieUserModel model = null;
		if(request.getSession().getAttribute(UserConstant.SESSION_NAME)!=null)
			model = (CookieUserModel) request.getSession().getAttribute(UserConstant.SESSION_NAME);
		if(model == null){
			model = getCookieUserByCookie(request);
		} 
		//由于session中也没有对loginname和nickname做编码所以此处也无需解码
//		else {
//			try {
//				model.setLoginName(URLDecoder.decode(model.getLoginName(), "UTF-8"));
//				if(model.getNickname() != null) {
//					model.setNickname(URLDecoder.decode(model.getNickname(), "UTF-8"));
//				}
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		return model;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
//		CookieUserModel cum = new CookieUserModel();
//		cum.setAppMark("haha");
//		cum.setUserID(1111L);
//		cum.setLoginName("hengheng");
//		String userModelJson = JsonUtil.toJsonString(cum);
//		//System.out.println(userModelJson);
//		userModelJson = EncryptUtils.encrypt(userModelJson, PASSWORD);
//		//System.out.println(userModelJson);
//		String encode = URLEncoder.encode(userModelJson, "UTF-8");
		//System.out.println(encode);
		String userModelJson = "{\"clientMark\":\"192.168.0.99\",\"drivceType\":0,\"loginName\":\"你好\",\"password\":\"lsMJugrkLWG53K6dMNjvA1\",\"userGroup\":1,\"userID\":1398766283382470}";
		System.out.println(userModelJson);
		System.out.println();
		userModelJson = EncryptUtils.encrypt(userModelJson, PASSWORD);
		System.out.println(userModelJson);
		System.out.println();
		//String encode = URLEncoder.encode(userModelJson, "UTF-8");
		//System.out.println(encode);
		//System.out.println();

	}
	/**
	 * 获得cookie user model
	 * 
	 * @param request
	 * @return
	 */
	public static CookieUserModel getCookieUserByCookie(HttpServletRequest request) {
		CookieUserModel model = null;
		Cookie[] cook = request.getCookies();
		if(cook!=null&&cook.length>0){
			
	
		for (Cookie cookie : cook) {
			if (UserConstant.COOKIE_NAME.equals(cookie.getName())) {
				try {
						String decodevalue = URLDecoder.decode(cookie.getValue(), "UTF-8");
						String cookiejson = EncryptUtils.decrypt(decodevalue,
							UserConstant.PASSWORD);
                        cookiejson = URLDecoder.decode(cookiejson, "UTF-8");
                        model = JSON.parseObject(cookiejson,CookieUserModel.class);
//						JSONObject jobj = JSONObject.fromObject(cookiejson);
//						model = (CookieUserModel) JSONObject.toBean(jobj,
//								CookieUserModel.class);
					} catch (Exception e) {
	
					}
				break;
			}
		}
		
		}
		// for IE7~9 or firfox
		if (model == null || cook == null || cook.length == 0) {
			String value = request.getParameter("uinfo");
			if (!StringUtils.isNullOrEmpty(value)) {
				try {
					String decodevalue = URLDecoder.decode(value, "UTF-8");
					String cookiejson = EncryptUtils.decrypt(decodevalue,
							UserConstant.PASSWORD);
                    cookiejson = URLDecoder.decode(cookiejson, "UTF-8");
//					JSONObject jobj = JSONObject.fromObject(cookiejson);
//
//					model = (CookieUserModel) JSONObject.toBean(jobj,
//								CookieUserModel.class);

                    model = JSON.parseObject(cookiejson,CookieUserModel.class);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		return model;
	}

	/**
	 * 清除cookie user model
	 * 
	 * @param request
	 * @param response
	 */
	public static void clearUserCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie newCookie = new Cookie(UserConstant.COOKIE_NAME, null); // 假如要删除名称为username的Cookie
		newCookie.setMaxAge(0); // 立即删除型
		newCookie.setPath("/"); // 项目所有目录均有效，这句很关键，否则不敢保证删除
		response.addCookie(newCookie); // 重新写入，将覆盖之前的
	}

	public static void setUserCookie(HttpServletRequest request,
			HttpServletResponse response, String value) {

	}

	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value) {
		setCookie(request, response, name, value, 0x278d00);
	}

	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value == null ? "" : value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(getPath(request));
		response.addCookie(cookie);
	}

	private static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return (path == null || path.length() == 0) ? "/" : path;
	}

}
