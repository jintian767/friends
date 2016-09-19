package com.wsx.friends.user.signup;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.core.model.OutputData;
import com.wsx.friends.user.UserBaseController;
import com.wsx.friends.user.signup.model.User;
import com.wsx.friends.user.signup.model.WebUser;
import com.wsx.friends.user.signup.model.WebUserOutputData;
import com.wsx.friends.user.signup.service.UserSignupService;

@RestController
@Scope("prototype")
public class UserSignupController extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(UserSignupController.class);
	
	
	@Autowired
	private UserSignupService userSignupService;
	
	@RequestMapping(
			value = "/signup/first",
			method = {RequestMethod.POST, RequestMethod.GET}
	)
	public String signup(User user, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		if (!checkUser(user)) {
			log.info("aaa", "bbb");
			throw new NullPointerException("abc");
			//return "mainSignup";
		}
		
		return "WEB-INF/jsp/user/signup";
	}
	
	@RequestMapping(
			value = "/signup/user",
			method = {RequestMethod.POST}
	)
	public String signupUser(User user, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		if (!checkUser(user)) {
			return "error";
		}
		boolean isSave = userSignupService.saveUser(user);
		if(isSave) {
			return "WEB-INF/jsp/user/signup";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(
			value = "/signup/web/user",
			method = {RequestMethod.POST}
	)
	public String signupWebUser(WebUser user,HttpServletRequest request, HttpServletResponse response) {
		if (!checkWebUser(user)) {
			return "error";
		}
		
		boolean isSave = userSignupService.saveWebUser(user);
		if(isSave) {
			return "WEB-INF/jsp/user/signupWeb";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(
			value = "/search/user/{userId}/{token}",
			method = {RequestMethod.GET}
	)
	public @ResponseBody OutputData getWebUser(@PathVariable("userId") String userId,
			@PathVariable("token") String token) {
		
		if (StringUtils.isEmpty(userId)) {
			return new OutputData(CODE_ERROR_CLIENT, STATUS_ERROR_PARAM);
		}
		
		WebUser user = userSignupService.getWebUserById(userId);
		
		WebUserOutputData data = new WebUserOutputData();
		data.setUsers(Arrays.asList(user));
		return data;
	}
	
	@RequestMapping(
			value = "/search/users/{userId}/{token}",
			method = {RequestMethod.GET}
	)
	public @ResponseBody OutputData getWebUsers(@PathVariable("userId") String userId,
			@PathVariable("token") String token) {
		
		if (StringUtils.isEmpty(userId)) {
			return new OutputData(CODE_ERROR_CLIENT, STATUS_ERROR_PARAM);
		}
		
		List<WebUser> users = userSignupService.getWebUsers(userId,token);
		
		WebUserOutputData data = new WebUserOutputData();
		data.setUsers(users);
		return data;
	}
	
	

}
