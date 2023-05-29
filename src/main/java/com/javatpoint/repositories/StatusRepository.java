package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.Status;

@Repository
public interface StatusRepository extends MongoRepository<Status, String> {
    
}
