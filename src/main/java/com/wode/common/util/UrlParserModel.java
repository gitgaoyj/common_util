package com.wode.common.util;

import com.wode.common.base.BaseModel;

/**
 * Created by wode-ui3 on 2014/10/30.
 */
public class UrlParserModel extends BaseModel {
    /**
     * 来源
     */
    private String orgRefer;
    /**
     * 实际来源，去除网盟等
     */
    private String refer;
    /**
     * 来源域名
     */
    private String domian;
    /**
     * 搜索关键字
     */
    private String keywords;
    /**
     * 来源站标题
     */
    private String title;
    /**
     * 搜索引擎
     */
    private String se;

    public String toString(){
        return "orgRefer:"+this.getOrgRefer()+"\n"+"refer:"+this.getRefer()+"\n"+"domian:"+this.getDomian()+"\n"+"keywords:"+this.getKeywords()
                +"\n"+"title:"+this.getTitle()+"\n"+"se:"+this.getSe();
    }


    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getDomian() {
        return domian;
    }

    public void setDomian(String domian) {
        this.domian = domian;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getOrgRefer() {
        return orgRefer;
    }

    public void setOrgRefer(String orgRefer) {
        this.orgRefer = orgRefer;
    }

}
