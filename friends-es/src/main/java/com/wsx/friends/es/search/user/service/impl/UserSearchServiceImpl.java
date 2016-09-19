package com.wsx.friends.es.search.user.service.impl;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.wsx.friends.es.search.user.model.User;
import com.wsx.friends.es.search.user.service.UserSearchService;
import com.wsx.friends.es.util.ElasticSearchUtil;

@Service
public class UserSearchServiceImpl implements UserSearchService {

	
	public User getPerson(String name) {
		User user = new User();
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setName("wang shuai xin" + name);
		user.setTitle("this is a test");
		
		Gson gson = new Gson();
		try {
			String data = gson.toJson(user);
			String id = ElasticSearchUtil.addDocument("user", "user", user.getId(), data);
			System.out.println(gson);
			
			Map<String, Object> map = ElasticSearchUtil.getDocumentByID("user", "user", id);
			System.out.println(map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return user;
	}
	
	/**
	 * 
	 */
	public List<Map<String, Object>> searchUsersByName(String userName) {
		return ElasticSearchUtil.getDocumentByContent("user", "user", userName);
	}
}
