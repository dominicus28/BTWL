package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.TimestampTelemetry;

@Repository
public interface TimestampTelemetryRepository extends MongoRepository<TimestampTelemetry, String> {}
