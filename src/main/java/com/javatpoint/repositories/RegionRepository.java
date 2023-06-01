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

    // Show all regions
    public List<Region> findAll() {
        return mongoTemplate.findAll(Region.class);
    }

    public List<Region> findRegionByProvince(String province) {
        Query query = new Query();
        query.addCriteria(Criteria.where("province").is(province));
        return mongoTemplate.find(query, Region.class);
    }

    public List<Region> findRegionByCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        return mongoTemplate.find(query, Region.class);
    }

    public Region findOneRegionByPostalCode(String postalCode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("postalCode").is(postalCode));
        return mongoTemplate.findOne(query, Region.class);
    }

    public Region save(Region region) {
        return mongoTemplate.save(region);  //insert to table
    }
}