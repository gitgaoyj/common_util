package com.wode.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * HttpClient请求工具类
 * @ClassName: HttpClientUtil 
 * @Description: TODO
 * @author hanfeng
 * @Date 2014年7月29日 下午10:57:28
 * @Version
 */
@Component
public class HttpClientUtil {
	
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	//private static ReloadableResourceBundleMessageSource messageSource;
	
	/**
	 * 提交http请求
	 * 
	 * @param sendType  提交类型 get post
	 * @param methodUrl 功能url（访问服务器域名之后的部分）
	 * @param paramMap  参数name-vlaue集合
	 */
	public static String sendHttpRequest(String sendType, String url, Map<String, Object> paramMap){
		String response =null;
		//http访问对象
		HttpClient client = new HttpClient();
		//开发环境
		//client.getHostConfiguration().setHost(url, port, protocol);
		//设置编码格式
		client.getParams().setContentCharset("UTF-8");
		//提交方法
		HttpMethod method = null;
		//判断访问方式
		//使用 POST 方式提交数据
		if("post".equals(sendType)) {
			method = getPostMethod(url, paramMap);    
			//使用 POST 方式提交数据
		} else {
			method = getGetMethod(url, paramMap);    
		}
		try {
			//提交请求
			client.executeMethod(method);
			logger.info("\n 服务器 响应Status	-----------		:" + method.getStatusLine());
			if(method.getStatusCode()==200){
				 response = new String(method.getResponseBodyAsString());
				//返回的信息
				logger.info("\n 服务器返回的信息	-----------		" + response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("\n HttpClientUtil 请求出现异常： --------------	" + e.getMessage());
		}finally{
			//释放请求连接
			method.releaseConnection();
		}
		return response;
	}

	public static String sendHttpsRequest(String sendType, String url, Map<String, Object> paramMap,String Authorization) {
		Protocol easyhttps = null;
		try {
			easyhttps = new Protocol("https", (ProtocolSocketFactory)new EasySSLProtocolSocketFactory(), 443);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Protocol.registerProtocol("https", easyhttps);
		String response =null;
		//http访问对象
		HttpClient client = new HttpClient();
		//开发环境
		//client.getHostConfiguration().setHost(url, port, protocol);
		//设置编码格式
		client.getParams().setContentCharset("UTF-8");
		//提交方法
		HttpMethod method = null;
		//判断访问方式
		//使用 POST 方式提交数据
		if("post".equals(sendType)) {
			method = getPostMethod(url, paramMap);
			//使用 POST 方式提交数据
		} else {
			method = getGetMethod(url, paramMap);
		}
		try {
			if(Authorization != null) {
				method.setRequestHeader("Authorization", Authorization);
			}
			//提交请求
			client.executeMethod(method);
			logger.info("\n 服务器 响应Status	-----------		:" + method.getStatusLine());
			if(method.getStatusCode()==200){
				response = new String(method.getResponseBodyAsString());
				//返回的信息
				logger.info("\n 服务器返回的信息	-----------		" + response);
			}


		} catch (Exception e) {
			e.printStackTrace();
			logger.info("\n HttpClientUtil 请求出现异常： --------------	" + e.getMessage());
		}finally{
			//释放请求连接
			method.releaseConnection();
		}
		return response;
	}
	public static String sendHttpsRequest(String sendType, String url, Map<String, Object> paramMap) {
		return sendHttpsRequest(sendType, url, paramMap, null);
	}

	public static String sendXMLDataByPost(String url, String xml) {
		Protocol easyhttps = null;
		try {
			easyhttps = new Protocol("https", (ProtocolSocketFactory)new EasySSLProtocolSocketFactory(), 443);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Protocol.registerProtocol("https", easyhttps);
		String response =null;
		//http访问对象
		HttpClient client = new HttpClient();
		//开发环境
		//client.getHostConfiguration().setHost(url, port, protocol);
		//设置编码格式
		client.getParams().setContentCharset("UTF-8");
		//提交方法
		HttpMethod method = getPostMethod(url,xml);
		//使用 POST 方式提交数据
		try {
			//提交请求
			client.executeMethod(method);
			logger.info("\n 服务器 响应Status	-----------		:" + method.getStatusLine());
			if(method.getStatusCode()==200){
				response = new String(method.getResponseBodyAsString());
				//返回的信息
				logger.info("\n 服务器返回的信息	-----------		" + response);
			}


		} catch (Exception e) {
			e.printStackTrace();
			logger.info("\n HttpClientUtil 请求出现异常： --------------	" + e.getMessage());
		}finally{
			//释放请求连接
			method.releaseConnection();
		}
		return response;
	}
	

	public static String sendJsonDataByPost(String url, String json,String Authorization) throws UnsupportedEncodingException {
		Protocol easyhttps = null;
		try {
			easyhttps = new Protocol("https", (ProtocolSocketFactory)new EasySSLProtocolSocketFactory(), 443);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Protocol.registerProtocol("https", easyhttps);
		String response =null;
		//http访问对象
		HttpClient client = new HttpClient();
		//开发环境
		//client.getHostConfiguration().setHost(url, port, protocol);
		//设置编码格式
		client.getParams().setContentCharset("UTF-8");
		//提交方法
		//使用 POST 方式提交数据
		HttpMethod method = getPostJsonMethod(url,json);
		try {
			if(Authorization != null) {
				method.setRequestHeader("Authorization", Authorization);
			}
			//提交请求
			client.executeMethod(method);
			logger.info("\n 服务器 响应Status	-----------		:" + method.getStatusLine());
			if(method.getStatusCode()==200){
				response = new String(method.getResponseBodyAsString());
				//返回的信息
				logger.info("\n 服务器返回的信息	-----------		" + response);
			}


		} catch (Exception e) {
			e.printStackTrace();
			logger.info("\n HttpClientUtil 请求出现异常： --------------	" + e.getMessage());
		}finally{
			//释放请求连接
			method.releaseConnection();
		}
		return response;
	}

	public static String sendDeleteData(String url, String Authorization) throws UnsupportedEncodingException {
		Protocol easyhttps = null;
		try {
			easyhttps = new Protocol("https", (ProtocolSocketFactory)new EasySSLProtocolSocketFactory(), 443);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Protocol.registerProtocol("https", easyhttps);
		String response =null;
		//http访问对象
		HttpClient client = new HttpClient();
		//开发环境
		//client.getHostConfiguration().setHost(url, port, protocol);
		//设置编码格式
		client.getParams().setContentCharset("UTF-8");
		//提交方法
		//使用 POST 方式提交数据
		HttpMethod method = getDeleteMethod(url);
		try {
			if(Authorization != null) {
				method.setRequestHeader("Authorization", Authorization);
			}
			//提交请求
			client.executeMethod(method);
			logger.info("\n 服务器 响应Status	-----------		:" + method.getStatusLine());
			if(method.getStatusCode()==200){
				response = new String(method.getResponseBodyAsString());
				//返回的信息
				logger.info("\n 服务器返回的信息	-----------		" + response);
			}


		} catch (Exception e) {
			e.printStackTrace();
			logger.info("\n HttpClientUtil 请求出现异常： --------------	" + e.getMessage());
		}finally{
			//释放请求连接
			method.releaseConnection();
		}
		return response;
	}
	/**
	 * 提供post方法
	 * 
	 * @return HttpMethod
	 */
	private static HttpMethod getPostMethod(String methodUrl,Map<String, Object> paramMap){
		PostMethod post = new PostMethod(methodUrl);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
		List<NameValuePair> paramPair = new ArrayList<NameValuePair>();   
		//定义数组下标
		int i = 0;
		for(Map.Entry<String, Object> entryMap : paramMap.entrySet()) {
			if(entryMap.getValue()!=null){
				NameValuePair nvPair = new NameValuePair(entryMap.getKey(), entryMap.getValue().toString());
				paramPair.add(nvPair) ;
				i++;
			}
		}
		//参数key-value数组
		NameValuePair[] paramArry =paramPair.toArray(new NameValuePair[paramPair.size()]);
		post.setRequestBody(paramArry);
		logger.info("\n**********		调用接口请求数据：" + Arrays.toString(paramArry));
		return post;
   	}
	/**
	 * 提供post方法
	 * 
	 * @return HttpMethod
	 */
	private static HttpMethod getDeleteMethod(String methodUrl){
		DeleteMethod post = new DeleteMethod(methodUrl);
		return post;
   	}


	/**
	 * 提供post方法
	 * 
	 * @return HttpMethod
	 */
	private static HttpMethod getPostMethod(String methodUrl,String xml){
		PostMethod post = new PostMethod(methodUrl);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
		RequestEntity requestEntity = new StringRequestEntity(xml);		
		post.setRequestEntity(requestEntity);
		logger.info("\n**********		调用接口请求数据：" + xml);
		return post;
   	}


	/**
	 * 提供post方法
	 * 
	 * @return HttpMethod
	 * @throws UnsupportedEncodingException 
	 */
	private static HttpMethod getPostJsonMethod(String methodUrl,String params) throws UnsupportedEncodingException{
		PostMethod post = new PostMethod(methodUrl);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
		RequestEntity requestEntity = new StringRequestEntity(params);
		post.setRequestEntity(requestEntity);
		return post;
   	}
	
	/**
	 * 提供get方法
	 * 
	 * @return HttpMethod
	 */
	private static HttpMethod getGetMethod(String methodUrl,Map<String, Object> paramMap){
		if(paramMap == null) {
			return new GetMethod(methodUrl);
		} else {
			//定义参数串
			StringBuffer paramStr = new StringBuffer("?");
			for(Map.Entry<String, Object> entryMap : paramMap.entrySet()) {
				paramStr.append(entryMap.getKey()).append("=").append(entryMap.getValue()).append("&");
			}
			paramStr.deleteCharAt(paramStr.length() - 1);
			return new GetMethod(methodUrl + paramStr);
		}
	}
	
	
	@Autowired
	public void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource) {
		//HttpClientUtil.messageSource = messageSource;
	}
	
	
}