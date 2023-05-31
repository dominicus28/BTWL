package com.javatpoint.repositories;

import org.springframework.stereotype.Repository;

import com.javatpoint.models.Region;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@Repository
public class RegionRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;
    
    public RegionRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Region> findRegionByProvince(String province) {
        Query query = new Query();
        query.addCriteria(Criteria.where("province").is(province));
        return mongoTemplate.find(query, Region.class);
    }
}