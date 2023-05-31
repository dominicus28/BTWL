package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.TimestampStatus;

@Repository
public interface TimestampStatusRepository extends MongoRepository<TimestampStatus, String> {}
