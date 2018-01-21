package com.wode.common.exception;

public class UserException extends BaseException{

	public UserException(String messageCode,Throwable innerException){
		//super(message);		
		super(messageCode,innerException);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
