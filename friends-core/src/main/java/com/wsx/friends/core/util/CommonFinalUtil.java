package com.wsx.friends.core.util;

import java.nio.charset.Charset;

/**
 * 公共常用静态最终的字符串定义
 * @author wangshuaixin
 *
 */
public final class CommonFinalUtil {

	//配置文件中我们的数据库连接用户key
	public static final String JDBC_USERNAME = "jdbc_username";
	//配置文件中我们数据库连接的密码key
	public static final String JDBC_PASSWORD = "jdbc_password";
	
	public static final String STRING_EMPTY = "";
	
	public static final String ENGLISH_SPIT = "-";
	
	public static final String CHARSET_STR = "UTF-8";
	
	public static final Charset CHARSET = Charset.forName(CHARSET_STR);
	
	public static final String HTTP = "http";
	
	public static final String HTTPS = "https";
	
	public static final String MD5 = "MD5";
}
