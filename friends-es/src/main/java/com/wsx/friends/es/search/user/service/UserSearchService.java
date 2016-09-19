package com.wsx.friends.es.search.user.service;

import java.util.List;
import java.util.Map;

import com.wsx.friends.es.search.user.model.User;

public interface UserSearchService {

	public User getPerson(String name);

	public List<Map<String, Object>> searchUsersByName(String userName);
}
