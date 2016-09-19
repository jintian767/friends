package com.wsx.friends.user.signup.mapper;

import java.util.List;

import com.wsx.friends.user.signup.model.User;

public interface UserMapper {

	public User getUserById(String id);
	
	public Integer saveUser(User user);
	
	public List<User> findAllUsers();
	
	public List<User> findByUserIds(List<Integer> userIds);
}
