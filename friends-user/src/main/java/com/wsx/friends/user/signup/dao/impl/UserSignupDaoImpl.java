package com.wsx.friends.user.signup.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wsx.friends.user.signup.dao.UserSignupDao;
import com.wsx.friends.user.signup.mapper.UserMapper;
import com.wsx.friends.user.signup.mapper.WebUserMapper;
import com.wsx.friends.user.signup.model.User;
import com.wsx.friends.user.signup.model.WebUser;

@Repository
public class UserSignupDaoImpl implements UserSignupDao {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private WebUserMapper webUserMapper;

	@Override
	public boolean saveUser(User user) {
		return userMapper.saveUser(user) > 0 ? true : false;
	}

	@Override
	public List<User> findAllUsers() {
		return userMapper.findAllUsers();
	}

	@Override
	public List<User> findByUserByIds(List<Integer> userIds) {
		return userMapper.findByUserIds(userIds);
	}
	
	@Override
	public boolean saveWebUser(WebUser user) {
		// TODO Auto-generated method stub
		return webUserMapper.saveWebUser(user) > 0 ? true : false;
	}
	
	@Override
	public WebUser getWebUserByID(String userId) {
		return webUserMapper.getWebUserByID(userId);
	}
	
	@Override
	public List<WebUser> findByWebUserByIds(List<String> ids) {
		return webUserMapper.findByWebUserIds(ids);
	}
}
