package com.wode.common.exception;

public class SystemException extends BaseException{

	public SystemException(String messageCode,Throwable innerException){
		//super(message);		
		super(messageCode,innerException);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
