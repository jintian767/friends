package com.wsx.friends.core.controller;

public interface WebController {
	
	/** 应答代码（成功）*/
	public final static int CODE_SUCCESS = 200;
	/** 应答代码（因客户端原因导致的失败）*/
	public final static int CODE_ERROR_CLIENT = 400;
	/** 应答代码（因服务端原因导致的失败）*/
	public final static int CODE_ERROR_SERVER = 500;
	
	/** 业务返回码（成功）*/
	public final static int STATUS_SUCCESS = 0;
	/** 业务返回码（未知错误）*/
	public final static int STATUS_ERROR_UNKNOWN = 1;
	/** 业务返回码（输入参数格式不正确）*/
	public final static int STATUS_ERROR_PARAM = 2;
	/** 业务返回码（验证码不正确）*/
	public final static int STATUS_ERROR_CHECKCODE = 3;
}
