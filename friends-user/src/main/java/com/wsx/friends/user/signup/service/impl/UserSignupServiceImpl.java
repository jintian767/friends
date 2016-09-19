package com.wsx.friends.user.signup.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.wsx.friends.core.redis.RedisClientTemplate;
import com.wsx.friends.user.signup.dao.UserSignupDao;
import com.wsx.friends.user.signup.model.User;
import com.wsx.friends.user.signup.model.WebUser;
import com.wsx.friends.user.signup.model.mongo.Person;
import com.wsx.friends.user.signup.service.UserSignupService;

@Service
@Transactional
public class UserSignupServiceImpl implements UserSignupService {
	
	@Autowired
	private UserSignupDao userSignupDao;
	
	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveUser(User user) {
		if (null == user) {
			return false;
		}
		Gson gson = new Gson();
		redisClientTemplate.set("user", gson.toJson(user), 2);
		System.out.println(redisClientTemplate.get("user"));
		
		
		Person person = new Person();
		person.set_id("12345hello");
		person.setName("wang");
		person.setHome("beijing");
		mongoTemplate.save(person, "person");
		
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.and("_id").is("12345hello");
		query.addCriteria(criteria);
		Person p = mongoTemplate.find(query, Person.class, "person").get(0);
		System.out.println(p);
		
		return userSignupDao.saveUser(user);
	}

	@Override
	public List<User> findAllUsers() {
		return userSignupDao.findAllUsers();
	}

	@Override
	public List<User> findByUserIds(List<Integer> userIds) {
		if (null == userIds) {
			return this.findAllUsers();
		}
		return userSignupDao.findByUserByIds(userIds);
	}
	
	@Override
	public boolean saveWebUser(WebUser user) {
		Gson gson = new Gson();
		redisClientTemplate.set("user-" + user.getUserId(), gson.toJson(user), 2);
		return userSignupDao.saveWebUser(user);
	}

	@Override
	public WebUser getWebUserById(String userId) {
		Person person = new Person();
		person.set_id(userId);
		person.setName("wang");
		person.setHome("beijing");
		mongoTemplate.save(person, "person");
		
		String userStr = redisClientTemplate.get("user-" + userId, 2);
		if (!StringUtils.isEmpty(userStr)) {
			Gson gson = new Gson();
			return gson.fromJson(userStr, WebUser.class);
		}
		
		return userSignupDao.getWebUserByID(userId);
	}
	
	@Override
	public List<WebUser> getWebUsers(String userId, String token) {
		List<String> ids = new ArrayList<String>(2);
		ids.add(userId);
		ids.add(token);
		return userSignupDao.findByWebUserByIds(ids);
	}
}
