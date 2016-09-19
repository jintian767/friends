package com.wsx.friends.zone.content.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.wsx.friends.zone.content.dao.ZoneContentDao;
import com.wsx.friends.zone.content.model.mongo.ZoneContent;

@Repository
public class ZoneContentDaoImpl implements ZoneContentDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean saveZoneContent(ZoneContent zoneContent) {
		mongoTemplate.save(zoneContent);
		return true;
	}
	
	public ZoneContent findOne(String id) {
		Criteria criteria = new Criteria();
		criteria.and("dataId").is(id);
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, ZoneContent.class);
	}

}
