package com.wode.common.web;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.ServletContextAware;

import com.wode.common.base.HeaderModel;
import com.wode.common.frame.base.BaseSpringController;
import com.wode.common.util.StringUtils;

public abstract class BaseController extends BaseSpringController implements ServletContextAware {
	
	public static String BASE_UPLOAD_PATH="/data/uploadDirResource";
	
	
	
	
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return String
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
		String ip = request.getHeader("x-forwarded-for");
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
	
	/**
	 * 下载用
	 * @param response
	 * @param file
	 * @throws IOException
	 */
	public void downloadFile(HttpServletResponse response,File file) throws IOException{
		response.setContentType("application/force-download");
		response.setContentLength((int)file.length());
		        //response.setContentLength(-1);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition","attachment; filename=\""+file.getName()+"\"");//fileName);
		//File f= new File(fileName);

		InputStream in = new FileInputStream(file);
		BufferedInputStream bin = new BufferedInputStream(in);
		DataInputStream din = new DataInputStream(bin);
		byte[] bt = new byte[0xfff]; 
		//while(din.available() > 0){
		int re = -1;
		while((re = din.read(bt))>0){
			response.getOutputStream().write(bt, 0, re);
//		    out.print(din.readLine());
//		    out.print("\n");
		}
		response.getOutputStream().flush();
	}
	
	/**
	 * 将header读入到HeaderModel中，同时也可以通过post方式来获得
	 * @param request
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public HeaderModel queryHeaderModel(HttpServletRequest request) {
		HeaderModel model = new HeaderModel();
		Field[] fields = model.getClass().getDeclaredFields();
		 for (int i = 0; i < fields.length; i++) {
	            // get header form http header or post/get
	            //int field = fields[i].getModifiers();
	            fields[i].setAccessible(true);
	            if(!StringUtils.isNullOrEmpty(request.getHeader(fields[i].getName()))){
	            	//fields[i].set(model, request.getHeader(fields[i].getName()));
                    if(fields[i].getType().isInstance(Long.class)){
                        try {
                            BeanUtils.setProperty(model, fields[i].getName(), Long.parseLong(request.getHeader(fields[i].getName())));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                    else
                        try {
                            BeanUtils.setProperty(model,fields[i].getName(),request.getHeader(fields[i].getName()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                }
	            else if(!StringUtils.isNullOrEmpty(request.getParameter(fields[i].getName()))){
                    try {
                        fields[i].set(model, request.getHeader(fields[i].getName()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
		 }
		return model;
	}
}
