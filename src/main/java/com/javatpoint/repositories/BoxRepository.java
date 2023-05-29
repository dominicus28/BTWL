package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.Box;

@Repository
public interface BoxRepository extends MongoRepository<Box, String> {
    
}
