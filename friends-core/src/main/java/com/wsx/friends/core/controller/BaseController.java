package com.wsx.friends.core.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


/**
 * 内部接口使用，便于session信息的验证
 * @author wangshuaixin
 *
 */
@RestController
@Scope("prototype")
public abstract class BaseController {

	@ExceptionHandler
	public String exceptionHandler(Exception ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		return "error";
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	protected static boolean checkToken(String token) {
		if (null == token) {
			return false;
		}
		if (token.length() < 34) {
			return false;
		}
		return true;
	}
}
