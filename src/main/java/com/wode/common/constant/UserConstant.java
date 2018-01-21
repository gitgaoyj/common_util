package com.wode.common.constant;


public class UserConstant {
	/**
	 * 记录的session名登录后
	 */
	public final static String SESSION_NAME = "UserModel";
	/**
	 * 记录用户信息cookie的名字
	 */
	public final static String COOKIE_NAME = "uinfo";
	/**
	 * 
	 */
	public final static String COOKIE_LOGINNAME = "uname";
	/**
	 * 针对cookie的加密字符串
	 */
	public final static String PASSWORD = "wode-cookie@password";
	/**
	 * 默认的没登录调转页面地址
	 */
	public final static String NOT_LOGIN = "notlogin";
	/**
	 * redis登录set中存储key的pc后缀名
	 */
	public static final String PC_SUFFIX = "_pc_login";
	/**
	 * redis登录set中存储key的移动端应用后缀名
	 */
	public static final String APP_SUFFIX = "_app_login";
	/**
	 * PC端同用户登录数量
	 */
	public static final int PC_LOGIN_COUNT = 1;
	/**
	 * 移动端相同应用相同用户登录数量
	 */
	public static final int APP_LOGIN_COUNT = 2;
	/**
	 * redis中对应登录set中value的登录后缀
	 */
	public final static String LOGIN_SUFFIX = "_login_suffix";
	/**
	 * redis中找回密码验证短信对应key后缀
	 */
	public final static String SENDMSGFINDPWD_SUFFIX = "_msgByFindPwd_suffix";
	/**
	 * redis中绑定手机号验证短信对应key后缀
	 */
	public final static String SENDMSGBINDPHONE_SUFFIX = "_msgByBindPhone_suffix";
	/**
	 * redis中手机号注册验证短信对应key后缀
	 */
	public final static String SENDMSGREGPHONE_SUFFIX = "_msgByRegPhone_suffix";
	/**
	 * redis中对应手机号对应功能发送短信的次数的后缀，跟在找回密码或者绑定手机号功能后缀的后面
	 */
	public final static String SENDMSGCOUNT_SUFFIX = "_count_suffix";
	/**
	 * redis中找回密码验证邮件对应key后缀
	 */
	public final static String SENDMAILFINDPWD_SUFFIX = "_mailByFindPwd_suffix";
	/**
	 * redis中绑定邮箱验证邮件对应key后缀
	 */
	public final static String SENDMAILBINDMAIL_SUFFIX = "_mailByBindMail_suffix";
	/**
	 * redis中注册邮箱验证邮件对应key后缀
	 */
	public final static String SENDMAILREGMAIL_SUFFIX = "_mailByRegMail_suffix";
	/**
	 * 邮件链接过期时间，默认半小时
	 */
	public final static int MAILEXPIRE_TIME = 60 * 30;
	/**
	 * 发送短信间隔时间
	 */
	public final static int SENDMSG_TIME = 60;
	/**
	 * 记录短信发送次数redis记录的过期时间，默认一天
	 */
	public final static int SENDMSGCOUNT_TIME = 60 * 60 * 24;
	/**
	 * 短信限制时间内最多发送次数
	 */
	public final static int SENDMSGCOUNT = 30;
	/**
	 * 通过找回密码方式通知验证类型
	 */
	public static final String FINDPWDFUNCTION = "findPwd";
	/**
	 * 通过注册手机找回密码方式通知验证类型
	 */
	public static final String FINDPWDBYPHONEFUNCTION = "findPwdByPhone";
	/**
	 * 通过注册邮箱找回密码方式通知验证类型
	 */
	public static final String FINDPWDBYEMAILFUNCTION = "findPwdByEmail";
	/**
	 * 通过注册邮箱找回密码方式通知验证类型
	 */
	public static final String CHANGEEMAILFUNCTION = "changeEmail";
	/**
	 * 通过绑定手机号方式通知验证类型
	 */
	public static final String BINDPHONEFUNCTION = "bindPhone";
	/**
	 * 通过绑定邮箱方式通知验证类型
	 */
	public static final String BINDMAILFUNCTION = "bindMail";
	/**
	 * 通过手机号注册方式通知类型
	 */
	public static final String REGPHONEFUNCTION = "regPhone";
	/**
	 * 通过邮箱注册方式通知类型
	 */
	public static final String REGEMAILFUNCTION = "regEmail";
	/**
	 * pc客户端类型
	 */
	public static final int PCDEVICETYPE = 0;
	/**
	 * ios客户端类型
	 */
	public static final int IOSDEVICETYPE = 1;
	/**
	 * android客户端类型
	 */
	public static final int ANDDEVICETYPE = 2;
	/**
	 * 记住登录状态cookie过期时间，默认一周
	 */
	public static final int COOKIEEXPTIME = 60 * 60 * 24 * 7;
	/**
	 * 用户上传头像默认临时路径
	 */
	public final static String UPLOAD_TEMP = "upload/temp";
	/**
	 * 用户上传头像真实服务器保存相对路径
	 */
	public final static String UPLOAD_USER = "userUpload/user";
	
	/**
	 * 个人设置中昵称字符最大长度
	 */
	public final static int NICKNAMEMAXLENGTH = 100;
	
	/**
	 * 个人设置中个人签名字符最大长度
	 */
	public final static int SIGNATUREMAXLENGTH = 140;
	/**
	 * 注册功能
	 */
	public final static String REGISTERFUNCTION = "register";
	/**
	 * 个人设置功能
	 */
	public final static String USERSETFUNCTION = "userset";
	/**
	 * 邮箱前缀
	 */
	public final static String EMAILPREFIX = "http://mail.";
	/**
	 * 用户禁用标志
	 */
	public final static String USER_DISABLED = "n";
	/**
	 * 用户启用标志
	 */
	public final static String USER_ENABLED = "y";
	/**
	 * 上传文件的最大容量(2m默认字节数)
	 */
	public final static int UPLOADFILE_MAXSIZE = 2 * 1024 * 1024;
	/**
	 * 新浪微博第三方登录类型
	 */
	public final static int SINATHIRDLOGIN = 0;
	/**
	 * QQ第三方登录类型
	 */
	public final static int QQTHIRDLOGIN = 1;
	/**
	 * 腾讯微博第三方登录类型
	 */
	public final static int TENCENTWEIBOLOGIN = 2;
	/**
	 * 人人第三方登录类型
	 */
	public final static int RENRENLOGIN = 3;
	/**
	 * web注册用户
	 */
	public final static int WEBUSERSOURCE = 1;
	/**
	 * 用户禁用状态码
	 */
	public final static String USERDISABLECODE = "3303";
	/**
	 * 用户类型中的公测类型
	 */
	public final static Long USERTYPE_BETA = new Long("10");
	/**
	 * 验证码已使用
	 */
	public final static int CODEISUSED = 1;
	/**
	 * 用户默认头像图片路径
	 */
	public final static String userDefaultAvatar = "assets/img/header-mrimg.png";
	/**
	 * redis登录set中存储key的IOS移动端后缀名
	 */
	public static final String IOS_LOGIN_SUFFIX = "_ios_login";
	/**
	 * redis登录set中存储key的Android移动端后缀名
	 */
	public static final String AND_LOGIN_SUFFIX = "_and_login";
	/**
	 * 服务器提交验证标示
	 */
	public final static String EMAIL_POSTFIXS = "EMAIL_POSTFIXS";
	/**
	 * ios系统名称
	 */
	public static final String IOSOS = "IOS";
	/**
	 * Android系统名称
	 */
	public static final String ANDOS = "Android";
	/**
	 * 用户密码最小长度
	 */
	public static final int PWDMINLEN = 6;
	/**
	 * 用户密码最大长度
	 */
	public static final int PWDMAXLEN = 16;
	/**
	 * 移动端上传图片最大宽度
	 */
	public static final int IMGMAXWIDTH = 200;
	/**
	 * 移动端上传图片最大高度
	 */
	public static final int IMGMAXHEIGHT = 200;
	/**
	 * 移动端昵称最大长度
	 */
	public static final int MOBILENICKNAMEMAXLEN = 100;
	
	/**
	 * 移动端个人签名最大长度
	 */
	public static final int MOBILESIGNATUREMAXLEN = 280;
	/**
	 * 用户类型的邀请码注册用户类型
	 */
	public static final long USERTYPE_INVITECODE = 6;
	
	/**
	 * 上传文件宽度
	 */
	public static final int UPLOADIMAGEWIDTH = 200;
	/**
	 * 上传文件高度
	 */
	public static final int UPLOADIMAGEHEIGHT = 200;
	/**
	 * 第三方登录用户其对应外朗用户昵称后缀最大值
	 */
	public static final int NICKSUFFIXMAXVALUE = 999;
	/**
	 * 用户注册时真实姓名最大长度
	 */
	public static final int REALNAMEMAXLENGTH = 64;
	/**
	 * 用户订书注册地址最大长度
	 */
	public static final int ORDERADDRESSMAXLENGTH = 400;
	/**
	 * 普通注册类型
	 */
	public static final String COMMONREG = "commonReg";
	
	/**
	 * 注册页面广告位位置别名
	 */
	public static final String REGADPOSITIONMARK = "reg";
	/**
	 * 登录页面广告位位置别名
	 */
	public static final String LOGINADPOSITIONMARK = "login";
	/**
	 * 用户注册链接
	 */
	public static final String USERREGLINK = "http://user.wode.com/user/toRegister.html?";
	/**
	 * 需要验证
	 */
	public static final String NEEDVALIDATE = "y";
	/**
	 * 无需验证
	 */
	public static final String NOINVALIDATE = "n";
	/**
	 * 合作商户引导注册成功来源
	 */
	public static final int BUSINESSSUCESS = 101;
	/**
	 * 合作商户引导注册失败来源
	 */
	public static final int BUSINESSFAIL = 6;
	/**
	 * http协议头
	 */
	public static final String HTTPSCHEME = "http://";
	/**
	 * https协议头
	 */
	public static final String HTTPSSCHEME = "https://";
	/**
	 * 平台标志令牌
	 */
	public static final String WEBTOKEN = "webtoken";
	/**
	 * redis中登录ue的过期时间
	 */
	public static final int LOGINUEEXTIME = 30 * 60;
	/**
	 * redis中24小时内短信发送记录
	 */
	public static final String SENDMSGMAP = "sendMsgMap";
	/**
	 * 记录用户登录后session值
	 */
	public final static String USER_SESSION = "userSession";
	/**
	 * web端记录用户未登录时购物车信息
	 */
	public final static String CART_COOKIE = "cartCookie";
	/**
	 * 记录用户注册手机号验证码信息
	 */
	public final static String PRODUCT_PHONECODE = "phoneFactory";
	/**
	 * 服务器提交验证标示
	 */
	public final static String COMFROM = "myFactory";
	
}
