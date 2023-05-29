package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.ParcelBrief;

@Repository
public interface ParcelBriefRepository extends MongoRepository<ParcelBrief, String> {}
