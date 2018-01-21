package com.wode.common.web.interceptor;

import static com.wode.common.util.StringUtils.isNullOrEmpty;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wode.common.base.CookieUserModel;
import com.wode.common.base.HeaderModel;
import com.wode.common.stereotype.NoLog;
import com.wode.common.util.StringUtils;
import com.wode.common.util.TimeUtil;
import com.wode.common.util.userAgent.UserAgent;
import com.wode.common.web.CookieUtil;

/**
 * 日志拦截器
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    public static String getIpAddr(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object handler, java.lang.Exception ex) throws java.lang.Exception {
        if (!(handler instanceof HandlerMethod))
            return;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        NoLog noLog = method.getAnnotation(NoLog.class);
        if (noLog != null) {
            return;
        }

        String protocol = request.getProtocol();
        //String scheme = request.getScheme();
        //String ServerName = request.getServerName();
        //int ServerPort = request.getServerPort();
        //String serverinfo = request.getServletContext().getServerInfo();
        //String RemoteAddr = request.getRemoteAddr();
        //String RemoteHost = request.getRemoteHost();
        //String ContentType = request.getContentType();
        //int ContentLength = request.getContentLength();
        //String AuthType = request.getAuthType();
        //String Method = request.getMethod();
        //String PathInfo = request.getPathInfo();
        //String PathTranslated = request.getPathTranslated();
        String QueryString = request.getQueryString()==null?"~":request.getQueryString();
        //String RemoteUser = request.getRemoteUser();
        //String RequestedSessionId = request.getRequestedSessionId();
        String RequestURL = request.getRequestURL().toString();
        //String ServletPath = request.getServletPath();
        //long CreationTime = request.getSession().getCreationTime();
        //long LastAccessedTime = request.getSession().getLastAccessedTime();
        //String Header = request.getHeader("Accept");
        //String host = request.getHeader("Host");
        String referer = request.getHeader("Referer")==null?"~":request.getHeader("Referer");
        String postReferer = "~";
        //if(StringUtils.isNullOrEmpty(referer)){
        postReferer = request.getParameter("Referer") == null?"~":request.getParameter("Referer");
        //}
        //String Accept_Language = request.getHeader("Accept-Language");
        //String Connection = request.getHeader("Connection");
        //String Cookie = request.getHeader("Cookie");
        //String userAgent = request.getHeader("User-Agent");
        UserAgent userAgent = new UserAgent(request.getHeader("user-agent"));
        HeaderModel headerModel = new HeaderModel();
        Field[] fields = headerModel.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            // get header form http header or post/get
            fields[i].setAccessible(true);
            if (!StringUtils.isNullOrEmpty(request.getHeader(fields[i].getName()))) {
                if(fields[i].getType().isInstance(Long.class)){
                    BeanUtils.setProperty(headerModel,fields[i].getName(),Long.parseLong(request.getHeader(fields[i].getName())));
                }
                else
                    BeanUtils.setProperty(headerModel,fields[i].getName(),request.getHeader(fields[i].getName()));
                //fields[i].set(headerModel, request.getHeader(fields[i].getName()));
            } else if (!StringUtils.isNullOrEmpty(request.getParameter(fields[i].getName()))) {
                //fields[i].set(headerModel, request.getHeader(fields[i].getName()));
                BeanUtils.setProperty(headerModel,fields[i].getName(),request.getParameter(fields[i].getName()));
            }
        }

        String networkMode = headerModel.getNetworkMode() == null ? "~" : headerModel.getNetworkMode();
        String deviceid = headerModel.getDeviceid() == null ? "~" : headerModel.getDeviceid();
        String devicetype = "~";
        if (headerModel.getDevicetype() == 1) {
            devicetype = "ios";
        } else if (headerModel.getDevicetype() == 2) {
            devicetype = "android";
        }

        String sversion = headerModel.getSversion() == null ? "~" : headerModel.getSversion();
        String cversion = headerModel.getCversion() == null ? "~" : headerModel.getCversion();
        String osversion = headerModel.getOsversion() == null ? "~" : headerModel.getOsversion();
        String resolution = headerModel.getResolution() == null ? "~" : headerModel.getResolution();
        String token = headerModel.getToken() == null ? "~" : headerModel.getToken();
        String appMark = headerModel.getAppMark() == null ? "~" : headerModel.getAppMark();

        CookieUserModel cookieUserModel = CookieUtil.getCookieUserByCookie(request);
        String userid = "~";
        String userName = "Anonymous(NotLogin)";
        if (!isNullOrEmpty(cookieUserModel)) {
            userid = cookieUserModel.getUserID() + "";
            userName = URLDecoder.decode(cookieUserModel.getLoginName(), "UTF-8");
            logger.info("CookieUserModel:userid="+userid+",userName="+userName);
        } else if (!isNullOrEmpty(headerModel.getUsername())) {
            userid = headerModel.getUserid() + "";
            userName = URLDecoder.decode(headerModel.getUsername(),"UTF-8");
            logger.info("HeaderModel:userid="+userid+",userName="+userName);
        }
        
        String ue = CookieUtil.getCookieString(request, "uv") == null ? "~" : CookieUtil.getCookieString(request, "uv");
        boolean isfirst = false;
        if(StringUtils.isNullOrEmpty(ue) ||"~".equals(ue)){
           
            Cookie newCookie = new Cookie("uv", StringUtils.getUUID());
            newCookie.setDomain(".wd-w.com");
            newCookie.setMaxAge(12*60*60); //12小时
            newCookie.setPath("/"); // 项目所有目录均有效，这句很关键，否则不敢保证删除
            response.addCookie(newCookie); // 重新写入，将覆盖之前的
            ue = newCookie.getValue();
            isfirst=true;
        }
        StringBuffer stringBuffer = new StringBuffer("###");
        stringBuffer.append(TimeUtil.getCurrentTime());
        stringBuffer.append("###");
        stringBuffer.append(userid);
        stringBuffer.append("###");
        stringBuffer.append(URLEncoder.encode(userName,"UTF-8"));
        stringBuffer.append("###");
        stringBuffer.append(ue);
        stringBuffer.append("###");
        stringBuffer.append(getIpAddr(request));
        stringBuffer.append("###");
        stringBuffer.append(userAgent.getOperatingSystem());
        stringBuffer.append("###");
        stringBuffer.append(userAgent.getBrowser());
        //详细的版本，比如userAgent.getBrowser()为chrome31    userAgent.getBrowserVersion()为31.0.1650.63
        stringBuffer.append(userAgent.getBrowserVersion());
        stringBuffer.append("###");
        stringBuffer.append(protocol);
        stringBuffer.append("###");
        if(!StringUtils.isNullOrEmpty(postReferer) && !"~".equals(postReferer))
            stringBuffer.append(postReferer);
        else
            stringBuffer.append(referer);
        stringBuffer.append("###");
        if(!StringUtils.isNullOrEmpty(postReferer) && !"~".equals(postReferer))
            stringBuffer.append(referer);
        else
            stringBuffer.append(RequestURL);
        stringBuffer.append("###");
        stringBuffer.append(QueryString);
        stringBuffer.append("###");
        stringBuffer.append(networkMode);
        stringBuffer.append("###");
        stringBuffer.append(deviceid);
        stringBuffer.append("###");
        stringBuffer.append(devicetype);
        stringBuffer.append("###");
        stringBuffer.append(osversion);
        stringBuffer.append("###");
        stringBuffer.append(cversion);
        stringBuffer.append("###");
        stringBuffer.append(sversion);
        stringBuffer.append("###");
        stringBuffer.append(resolution);
        stringBuffer.append("###");
        stringBuffer.append(token);
        stringBuffer.append("###");
        stringBuffer.append(appMark);
        stringBuffer.append("###");
        if(!StringUtils.isNullOrEmpty(postReferer) && !"~".equals(postReferer))
            stringBuffer.append(RequestURL);
        stringBuffer.append("###");
        stringBuffer.append(isfirst);
        stringBuffer.append("###");
        if(!StringUtils.isNullOrEmpty(request.getParameter("sheight")))
            stringBuffer.append(request.getParameter("sheight"));
        else
            stringBuffer.append("~");
        stringBuffer.append("###");
        if(!StringUtils.isNullOrEmpty(request.getParameter("swidth")))
            stringBuffer.append(request.getParameter("swidth"));
        else
            stringBuffer.append("~");
        stringBuffer.append("###");

        logger.info(stringBuffer.toString());
        return ;//super.preHandle(request, response, handler);
    }
}
