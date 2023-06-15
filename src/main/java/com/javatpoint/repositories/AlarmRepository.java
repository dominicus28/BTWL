package com.javatpoint.repositories;


import org.springframework.stereotype.Repository;

import com.javatpoint.models.Alarm;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;


@Repository
public class AlarmRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public AlarmRepository(MongoTemplate mongoTemplate) {
        // super();
        this.mongoTemplate = mongoTemplate;
    }

    public Alarm find(int code) {
        Query query = new Query();
        query.addCriteria(Criteria.where("code").is(code));
        return mongoTemplate.findOne(query, Alarm.class);
    }
} 
