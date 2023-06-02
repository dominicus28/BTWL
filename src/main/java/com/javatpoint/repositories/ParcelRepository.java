package com.javatpoint.repositories;



import java.util.ArrayList;
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
import com.javatpoint.models.ParcelComplete;
import com.javatpoint.models.Region;
import com.javatpoint.models.Status;

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

    // find parcels by region (city) - for courier mode
    // public List<Parcel> findParcelsToAssignbyCity(String city) {
    //     /* Find regions with specyfied city */
    //     Query regionQuery = new Query();
    //     regionQuery.addCriteria(Criteria.where("city").is(city));
    //     List<Region> regions = mongoTemplate.find(regionQuery, Region.class); 

    //     /* Find code 1 */
    //     Query statusQuery = new Query();
    //     statusQuery.addCriteria(Criteria.where("code").is(1));
    //     Status status = mongoTemplate.findOne(statusQuery, Status.class); 

    //     List<Parcel> fullParcelList = new ArrayList<>();
    //     for (Region region : regions) {
    //         db.collection.find({

    //         })

    //         Query query = new Query();
    //         query.addCriteria(Criteria.where("deliverFrom.region.id").is(region.getId().toString()));
    //         /* Code must be 1*/
    //         query.addCriteria(Criteria.where("status.id").is(status.getId().toString()));
    //         mongoTemplate.find(query, ParcelComplete.class); 

    //         fullParcelList
    //     }
        
    //     return fullParcelList;
    // }
}
