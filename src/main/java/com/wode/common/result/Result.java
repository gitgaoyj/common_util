package com.wode.common.result;

import java.io.Serializable;

import cn.org.rapid_framework.page.Page;

/**
 * 所有接口的返回类,判断errorcode是否为0,来判断是会否含有错误.并含有分页信息
 * @author mengkaixuan
 *
 */
public class Result implements Serializable{
	/**
	 *   分页信息，总数
	 */
	private Integer total;
    /**
     * 分页信息,当前页
     */
	private Integer page;
    /**
     * 分页信息,当前size
     */
	private Integer size;
    /**
     * 分页信息,当前总条数
     */
	private Integer totalPage;
    /**
     * 返回的真正信息
     */
	private Object msgBody;
    /**
     * 错误Key
     */
	private String key;
    /**
     * 错误码,当ErrorCode==0时,表示没有错误
     */
	private String errorCode;
    /**
     * 当含有错误信息的时候,返回错误信息
     */
	private String message;
	
	public Result() {
		super();
	}
	
	public Result(Page page) {
		if(page != null){
			this.setMsgBody(page.getResult());
			this.setPage(page.getThisPageNumber());
			this.setTotal(page.getTotalCount());
			this.setTotalPage(page.getLastPageNumber());
			this.setSize(page.getPageSize());
			this.setErrorCode("0");
		}else{
			this.setErrorCode("300");
			this.setMsgBody("");
		}
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
		//if(size != 0){
		if(size != null && 0!=size){
			this.totalPage = total%size==0?total/size:total/size+1;
		}		
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
		//if(total != 0){
		if(total != null && 0!=total){
			this.totalPage =total%size==0?total/size:total/size+1;
		}
	}
//	public static void main(String[] args){
//		Result rt  =  new Result();
//		rt.setTotal(58);
//		rt.setSize(10);
//		System.out.println(rt.getTotalPage());		
//	}
//	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Object getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(Object msgBody) {
		this.msgBody = msgBody;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		if(errorCode.startsWith("0x")){
			this.errorCode = Integer.parseInt(errorCode.substring(2), 16)+"";
			return;
		}
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
