package com.bilport.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.FeedbackFile;

@Repository
public interface FeedbackRepository extends MongoRepository<FeedbackFile, String>{
    
}
