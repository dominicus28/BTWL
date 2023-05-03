package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.ParcelStore;

@Repository
public interface ParcelStoreRepository extends MongoRepository<ParcelStore, String> {
    
}
