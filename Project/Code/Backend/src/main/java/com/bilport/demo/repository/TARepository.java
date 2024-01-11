package com.bilport.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.TA;

@Repository
public interface TARepository extends MongoRepository<TA, String> {
    Optional<TA> findByUserName(String userName);

}
