package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.TimestampAlarm;

@Repository
public interface TimestampAlarmRepository extends MongoRepository<TimestampAlarm, String> {}
