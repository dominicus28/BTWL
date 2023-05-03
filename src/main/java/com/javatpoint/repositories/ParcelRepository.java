package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.Parcel;

@Repository
public interface ParcelRepository extends MongoRepository<Parcel, String> {
    
}
