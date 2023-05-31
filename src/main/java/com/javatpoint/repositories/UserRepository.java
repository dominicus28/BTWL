package com.javatpoint.repositories;

import org.springframework.stereotype.Repository;
// import org.springframework.data.mongodb.repository.MongoRepository;
import com.javatpoint.models.User;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;


@Repository
public class UserRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;
    
    public UserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public User findOne(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    public List<User> findAll(){
        return mongoTemplate.findAll(User.class);
    }

    public User save(User user){
        return mongoTemplate.save(user);
    }
}
