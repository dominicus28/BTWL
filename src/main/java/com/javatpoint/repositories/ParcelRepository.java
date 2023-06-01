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
import com.javatpoint.models.Parcel;

@Repository
public class ParcelRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public ParcelRepository(MongoTemplate mongoTemplate) {
        //super();
        this.mongoTemplate = mongoTemplate;
    }

    public Parcel save(Parcel parcel) {
        return mongoTemplate.save(parcel);
    }

    public Parcel findOne(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Parcel.class);
    }

    public List<Parcel> findAll() {
        return mongoTemplate.findAll(Parcel.class);
    }

    public Parcel setBox(String id, Box box) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update().set("box", box.getId());
        return mongoTemplate.findAndModify(query, update, Parcel.class);
    }

    public Parcel setBox(String id, String boxId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update().set("box.id", boxId);
        return mongoTemplate.findAndModify(query, update, Parcel.class);
    }

    // find parcels by region (city)
    public List<Parcel> findParcelsbyCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("region.city").is(city));
        return mongoTemplate.find(query, Parcel.class); 
    }
}
