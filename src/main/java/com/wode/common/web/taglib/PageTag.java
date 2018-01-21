package com.wode.common.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer totalPage;
	private Integer currentPage;
	//分页条中有多少个超链接 
	private Integer showPageNumber = 10;
	private Integer pageSize;
	
	private String url;

	public int doStartTag(){
		if(totalPage == null || totalPage <=1 ) return EVAL_PAGE;
		try {
			JspWriter out1 = pageContext.getOut();
			StringBuffer outStr = new StringBuffer("<div class=\"page\">");
			//当前页 
			int pagenow=currentPage!=null?currentPage:1;
			//中间的那个超链接距离边缘链接的间隔a的个数 例如：共11个分页 那么这个就是5
			int a_padding=(int)Math.ceil(showPageNumber/2);  
			if(totalPage-pagenow<=a_padding&&pagenow>a_padding+1){
				a_padding=showPageNumber-(totalPage-pagenow);
			} 
			int start=pagenow-a_padding;
			int end=start+showPageNumber; 
			if(pagenow<=1){
				outStr.append("<a href=\"javascript:void(0);\">前一页</a>");
			}else{
				int prev = pagenow-1;
				String nowurl = url.replace("[page]", (pagenow-1)+"");
				nowurl = nowurl.replace("[size]", pageSize +"");			
				outStr.append("<a href=\"javascript:void(0);\" onclick=\"formSubmit('"+prev+"');\">前一页</a>");
			}
			for (int i = start; i <=end; i++) { 
				if(i<=0){
					end+=Math.abs(i);
					i=1;
				} 
				String nowurl = url.replace("[size]", pageSize +"");
				if(pagenow == i){
					outStr.append("<a class=\"active\"> ");
				}else{
					outStr.append("<a>");
				}
				outStr.append(" href=\"javascript:void(0);\" onclick=\"formSubmit('"+i+"');\">"+i+"</a></li>");
				
				if(i==totalPage){
					break;
				} 
			} 
			if(pagenow>=totalPage){
				outStr.append("<a href=\"javascript:void(0);\" class=\"disabled\">后一页</a>");
			}else{
				outStr.append("");
				String nowurl = url.replace("[page]", (pagenow+1)+"");
				nowurl = nowurl.replace("[size]", pageSize +"");		
				outStr.append("<a href=\"javascript:void(0);\" onclick=\"formSubmit('"+pagenow+1+"');\">"+pagenow+1+"</a>");
			}
			outStr.append("<span>共"+totalPage+"页</span>");
			outStr.append("<input type=\"text\" id=\"pagenum\" name=\"pagenum\" class=\"input_text\"/><span>页</span>");
			outStr.append("<input type=\"button\" value=\"确定\" class=\"input_btn\" onclick=\"gotoPage();\"/>");
			outStr.append("</div>");
			
			out1.print(outStr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public int doEndTag() {
		return EVAL_PAGE;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getShowPageNumber() {
		return showPageNumber;
	}

	public void setShowPageNumber(Integer showPageNumber) {
		this.showPageNumber = showPageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}