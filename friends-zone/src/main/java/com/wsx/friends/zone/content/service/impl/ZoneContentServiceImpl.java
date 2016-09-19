package com.wsx.friends.zone.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.wsx.friends.core.redis.RedisClientTemplate;
import com.wsx.friends.zone.content.dao.ZoneContentDao;
import com.wsx.friends.zone.content.model.mongo.ZoneContent;
import com.wsx.friends.zone.content.service.ZoneContentService;

@Service
public class ZoneContentServiceImpl implements ZoneContentService {
	
	@Autowired
	private ZoneContentDao zoneContentDao;
	
	@Autowired
	private RedisClientTemplate redisClientTemplate;

	@Override
	public boolean saveZoneContent(ZoneContent zoneContent) {
		
		testRedis(zoneContent);
		
		return zoneContentDao.saveZoneContent(zoneContent);
	}

	private void testRedis(ZoneContent zoneContent) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		redisClientTemplate.set(zoneContent.getTitle(), gson.toJson(zoneContent), 4);
	}

	
	
}
