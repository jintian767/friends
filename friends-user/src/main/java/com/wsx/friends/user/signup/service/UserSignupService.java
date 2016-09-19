package com.wsx.friends.user.signup.service;

import java.util.List;

import com.wsx.friends.user.signup.model.User;
import com.wsx.friends.user.signup.model.WebUser;

public interface UserSignupService {

	public User getUserById(String id);
	
	public boolean saveUser(User user);
	
	public List<User> findAllUsers();
	
	public List<User> findByUserIds(List<Integer> userIds);

	public boolean saveWebUser(WebUser user);

	public WebUser getWebUserById(String userId);

	public List<WebUser> getWebUsers(String userId, String token);
}
