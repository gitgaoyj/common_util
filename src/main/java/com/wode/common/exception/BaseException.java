package com.wode.common.exception;


public class BaseException extends RuntimeException{

	private String code;	
	private String messageCode;
	public BaseException(){}
	public BaseException(String messageCode,Throwable innerException){
		//loead.getMessage(MessageSourceResolvable, arg1);		
		super(innerException);
		this.setMessageCode(messageCode);
	
	}
	
	
	@Override
	public String getMessage() {
		//String msg = messageSource.getMessage(getMessageCode()+".message", null,"Default",null);
		//if(!StringUtils.isNullOrEmpty(msg))
		//	return msg;
		return super.getMessage();
	}	


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	



	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}






	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
