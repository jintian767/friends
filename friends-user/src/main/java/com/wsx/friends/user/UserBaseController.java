package com.wsx.friends.user;

import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.core.controller.BaseController;
import com.wsx.friends.core.controller.WebController;
import com.wsx.friends.core.util.UUIDUtils;
import com.wsx.friends.user.login.model.LoginUser;
import com.wsx.friends.user.signup.model.User;
import com.wsx.friends.user.signup.model.WebUser;

@RestController
@Scope("prototype")
@RequestMapping("/User")
public abstract class UserBaseController extends BaseController implements WebController {

	protected boolean checkUser(User user) {
		if (null == user) {
			return false;
		}
		
		if (StringUtils.isEmpty(user.getName())) {
			return false;
		}
		
		return true;
	}
	
	protected boolean checkWebUser(WebUser user) {
		if (null == user) {
			return false;
		}
		if (StringUtils.isEmpty(user.getUserName())) {
			return false;
		}
		if (StringUtils.isEmpty(user.getUserId())) {
			user.setUserId(UUIDUtils.getUUID());
		}
		return true;
	}
	
	protected int checkLogin(LoginUser user) {
		if (null == user) {
			return WebController.STATUS_ERROR_CHECKCODE;
		}
		if (StringUtils.isEmpty(user.getCheckCode())) {
			return WebController.STATUS_ERROR_CHECKCODE;
		}
		
		if (StringUtils.isEmpty(user.getUserCode()) ||
				StringUtils.isEmpty(user.getPassword())) {
			return WebController.STATUS_ERROR_PARAM;
		}
		return WebController.STATUS_SUCCESS;
	}
	
	protected void reconstructPasswd(LoginUser user, boolean recon) {
		String password = user.getPassword();
		if (recon) {
			
		} 
		user.setPassword(password);
	}
}
