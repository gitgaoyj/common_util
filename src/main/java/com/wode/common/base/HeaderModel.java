package com.wode.common.base;

/**
 * http header's or post
 * @author wode
 *
 */
public class HeaderModel {
    /**
     * 客户端推送字符串
     */
	private String token;
    /**
     * 客户端软件版本
     */
	private String cversion;
    /**
     * 服务器版本
     */
	private String sversion;
    /**
     * 客户端ip
     */
	private String cip;
	/**
	 * 此项目不要修改名称，在cookie还要使用
     * 客户端唯一标示
	 */
	private String deviceid;
    /**
     * 客户端类型 ios,android(1,2)
     */
	private int devicetype;
    /**
     * 客户端分辨率
     */
    private String resolution;
    /**
     * 客户端连网方式 WIFI还是移动供应商
     */
    private String networkMode;
    /**
     * 客户端操作系统版本
     */
	private String osversion;
	/**
	 * 应用别名
	 */
	private String appMark;
	
	/**
	 * 为了移动端提供的userid，此处
	 */
	private Long userid;
    private String username;
    //用户类型
    private String usertype;
    @Deprecated
	private String isM;
	/**
	 * 登录名称
	 */
	private String loginName;
	@Deprecated
	private String password;
	/**
	 * 360    	代表  360手机助手 
	 * WDJ 	代表  豌豆荚 
	 * BD    	代表  百度手机助手 
	 * YYB  	代表  应用宝  
	 * AZ    	代表  安智市场 
	 * YL     	代表  公司服务器下载
	 */
	private String market_type;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCversion() {
		return cversion;
	}
	public void setCversion(String cversion) {
		this.cversion = cversion;
	}
	public String getSversion() {
		return sversion;
	}
	public void setSversion(String sversion) {
		this.sversion = sversion;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getNetworkMode() {
        return networkMode;
    }

    public void setNetworkMode(String networkMode) {
        this.networkMode = networkMode;
    }

    public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAppMark() {
		return appMark;
	}
	public void setAppMark(String appMark) {
		this.appMark = appMark;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
	public String getMarket_type() {
		return market_type;
	}
	public void setMarket_type(String market_type) {
		this.market_type = market_type;
	}
    
    
}
