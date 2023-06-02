package com.javatpoint.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.Box;
import com.javatpoint.models.ParcelComplete;
import com.javatpoint.models.TimestampAlarm;
import com.javatpoint.models.TimestampTelemetry;

@Repository
public class BoxRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public BoxRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    public Box findOne(String mac) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mac").is(mac));
        return mongoTemplate.findOne(query, Box.class);
    }

    public List<Box> findAll() {
        return mongoTemplate.findAll(Box.class);
    }

    public Box save(Box box) {
        return mongoTemplate.save(box);
    }

    public Box setParcelComplete(String mac, ParcelComplete parcelComplete) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mac").is(mac));
        Update update = new Update().set("parcelComplete", parcelComplete);
        return mongoTemplate.findAndModify(query, update, Box.class);
    }

    public Box setParcelComplete(String mac, String parcelCompleteId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mac").is(mac));
        Update update = new Update().set("parcelComplete.id", parcelCompleteId);
        return mongoTemplate.findAndModify(query, update, Box.class);
    }

    public Box setParcelCompleteNull(String mac) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mac").is(mac));
        Update update = new Update().set("parcelComplete", null);
        return mongoTemplate.findAndModify(query, update, Box.class);
    }

    public Box addTelemetry(String mac, TimestampTelemetry newTimestampTelemetry) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mac").is(mac));
        Update update = new Update().addToSet("telemetry", newTimestampTelemetry);
        return mongoTemplate.findAndModify(query, update, Box.class);
    }

    public Box addAlarm(String mac, TimestampAlarm newTimestampAlarm) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mac").is(mac));
        Update update = new Update().addToSet("alarm", newTimestampAlarm);
        return mongoTemplate.findAndModify(query, update, Box.class);
    }
}
