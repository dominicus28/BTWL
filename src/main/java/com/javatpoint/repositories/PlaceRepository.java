package com.javatpoint.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.models.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {}
