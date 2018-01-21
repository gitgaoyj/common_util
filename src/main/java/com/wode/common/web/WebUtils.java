package com.wode.common.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebUtils {
	private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);//Logger.getLogger(WebUtils.class);
	
	private static  String SnapPath="/opt/htmlSnap";
	
	/**
	 * 生成快照方法
	 * 此方法也可以用于生成首页等其他页面
	 * @param url 要生成的快照地址
	 * @param path 保存的路径
	 * @return 返回生成的快照相对路径
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String createSnapshoot(String url,String path,boolean isUseDefaultName) throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
//		HttpClientParams param = new HttpClientParams();
		HttpMethod method = new GetMethod(url);
		logger.info("begin to create Snapshoot:"+url);
		client.getHttpConnectionManager().getParams()
		.setConnectionTimeout(30000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
//		int statusCode = client.executeMethod(method);
		byte[] responseBody = null;
		responseBody = method.getResponseBody();
		//String result = new String(responseBody);
		File srcFile = null;
		String fileName = "/"+new SimpleDateFormat("YY/MM/dd").format(new Date())+"/"+new Date().getTime()+".html";
		if(!isUseDefaultName){			
			srcFile = new File(SnapPath+path+fileName);
		}else{
			com.wode.common.util.FileUtils.mkDir(new File(path));
			srcFile = new File(path);
		}
		org.apache.commons.io.FileUtils.writeByteArrayToFile(srcFile, responseBody);
        method.releaseConnection();
		logger.info("end to create Snapshoot:"+srcFile.getAbsolutePath());
		if(!isUseDefaultName)
			return fileName;
		return srcFile.getPath();
	}
	
	/**
	 * 生成快照方法
	 * 此方法也可以用于生成首页等其他页面
	 * @param url 要生成的快照地址
	 * @param path 保存的路径
	 * @return 返回生成的快照相对路径
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String createSnapshootNew(String url,String path,boolean isUseDefaultName) throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
//		HttpClientParams param = new HttpClientParams();
		HttpMethod method = new GetMethod(url);
		logger.info("begin to create Snapshoot:"+url);
		client.getHttpConnectionManager().getParams()
		.setConnectionTimeout(30000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
//		int statusCode = client.executeMethod(method);
		byte[] responseBody = null;
		responseBody = method.getResponseBody();
		//String result = new String(responseBody);
		File srcFile = null;
		String fileName = "/"+new SimpleDateFormat("YY/MM/dd").format(new Date())+"/"+new Date().getTime()+".html";
		if(!isUseDefaultName){			
			srcFile = new File(SnapPath+"/snapshot"+fileName);
		}else{
			com.wode.common.util.FileUtils.mkDir(new File(path));
			srcFile = new File(path);
		}
		org.apache.commons.io.FileUtils.writeByteArrayToFile(srcFile, responseBody);		
		logger.info("end to create Snapshoot:"+srcFile.getAbsolutePath());
        method.releaseConnection();
		if(!isUseDefaultName)
			return fileName;
		return srcFile.getPath();
	}
	
	public static void main(String[] args) throws HttpException, IOException{
		System.out.println(createSnapshoot("http://www.126.com","upload/url",false));
	}
}
