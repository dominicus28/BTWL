package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.Telemetry;

@Repository
public interface TelemetryRepository extends MongoRepository<Telemetry, String> {
    
}
