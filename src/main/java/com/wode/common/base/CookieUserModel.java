package com.wode.common.base;


import java.io.Serializable;
import java.util.Date;

import com.wode.common.util.StringUtils;

public class CookieUserModel implements Serializable{
	private Long UserID;
	private long UserGroup;
	private String password;
	private String LoginName;
	private String username;
	private String phonenumber;
	private Integer usercom;
	/**
	 * 如果是PC端，此项目为IP
	 * 如果是移动端此项目为driverid
	 */
	private String clientMark;
	/**
	 * 0=pc，1=ios，2=android，3=other
	 */
	private int drivceType;
	/**
	 * 应用别名
	 */
	private String appMark;
	/**
	 * 上次登录时间
	 */
	private String lastLogin;
	/**
	 * 标志唯一客户端的标志，根据浏览器区分
	 */
	private String ue;
	/**
	 * 用户上传头像路径
	 */
	private String avatar;
	/**
	 * 会员级别
	 */
	private Integer memberlevel;
	/**
	 * 会员有效期
	 */
	private Date memberendtime;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 平台标志令牌
	 */
	private String webtoken;

    /**
     * 当前登录时间
     */
    private Date loginTime;

    @Override
    public boolean equals(Object obj){
        CookieUserModel model = (CookieUserModel) obj;

        if(!StringUtils.isEquals(this.getUserID(),model.getUserID()))
            return false;
        if(!StringUtils.isEquals(this.getAvatar(),model.getAvatar()))
            return false;
        if(!StringUtils.isEquals(this.getLoginName(),model.getLoginName()))
            return false;
//        if(!StringUtils.isEquals(this.getAppMark(),model.getAppMark()))
//            return false;
        if(!StringUtils.isEquals(this.getPassword(),model.getPassword()))
            return false;
        //if(!StringUtils.isEquals(this.getLastLogin(),model.getLastLogin()))
        //    return false;
        if(!StringUtils.isEquals(this.getMemberlevel(),model.getMemberlevel()))
            return false;
        //if(!StringUtils.isEquals(this.getUe(),model.getUe()))
        //    return false;
        if(!StringUtils.isEquals(this.getUserGroup(),model.getUserGroup()))
            return false;
	    return StringUtils.isEquals(this.getDrivceType(), model.getDrivceType());
    }

	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public long getUserGroup() {
		return UserGroup;
	}
	public void setUserGroup(long userGroup) {
		UserGroup = userGroup;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	
	public int getDrivceType() {
		return drivceType;
	}
	public void setDrivceType(int drivceType) {
		this.drivceType = drivceType;
	}
	public String getClientMark() {
		return clientMark;
	}
	public void setClientMark(String clientMark) {
		this.clientMark = clientMark;
	}
	public String getAppMark() {
		return appMark;
	}
	public void setAppMark(String appMark) {
		this.appMark = appMark;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUe() {
		return ue;
	}
	public void setUe(String ue) {
		this.ue = ue;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getMemberlevel() {
		return memberlevel;
	}
	public void setMemberlevel(Integer memberlevel) {
		this.memberlevel = memberlevel;
	}

	public Date getMemberendtime() {
		return memberendtime;
	}

	public void setMemberendtime(Date memberendtime) {
		this.memberendtime = memberendtime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWebtoken() {
		return webtoken;
	}

	public void setWebtoken(String webtoken) {
		this.webtoken = webtoken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

	public Integer getUsercom() {
		return usercom;
	}

	public void setUsercom(Integer usercom) {
		this.usercom = usercom;
	}


}
