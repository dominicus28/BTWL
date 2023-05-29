package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.ParcelComplete;

@Repository
public interface ParcelCompleteRepository extends MongoRepository<ParcelComplete, String> {}
