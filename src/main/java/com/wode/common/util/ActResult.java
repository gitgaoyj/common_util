package com.wode.common.util;

import java.io.Serializable;

public class ActResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean success=true;
	
	//private int code;
	
	private String msg="";
	
	private T data;
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public static ActResult success(Object data){
		ActResult ret=	new ActResult();
		ret.setData(data);
		return ret;	
	}
	
	public static ActResult fail(String msg){
		ActResult ret=	new ActResult();
		ret.setSuccess(false);
		ret.setMsg(msg);
		return ret;	
	}
	
	public static ActResult successSetMsg(String msg){
		ActResult ret=	new ActResult();
		ret.setSuccess(true);
		ret.setMsg(msg);
		return ret;	
	}
}
