package com.wode.common.frame.base;

import cn.org.rapid_framework.page.PageRequest;

public class BaseQuery extends PageRequest implements java.io.Serializable {
	private static final long serialVersionUID = -360860474471966681L;
	public static final int DEFAULT_PAGE_SIZE = 10;

    public Boolean isHasQuery() {
        return isHasQuery;
    }

    public void setHasQuery(Boolean hasQuery) {
        isHasQuery = hasQuery;
    }

    public void setPageSizeNoMax(int pageSize) {
        super.setPageSize(pageSize);
    }
    //
    public void setPageSize(int pageSize){
        if(pageSize<0) pageSize=10;
        if(pageSize > 100) pageSize = 100;
        super.setPageSize(pageSize);
    }
    
    public int getPageNumber() {
		int pageNumber =super.getPageNumber();
		if(pageNumber<=0) pageNumber=1;
        return pageNumber;
	}
    
    /**
     * add by mengkaixuan
     * 在page_where 中追加  isHasQuery 那么在加入and 1=1
     */
    protected Boolean isHasQuery;
	
    static {
        System.out.println("BaseQuery.DEFAULT_PAGE_SIZE="+DEFAULT_PAGE_SIZE);
    }
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}
	  
}
