package com.bilport.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.Evaluator;

@Repository
public interface EvaluatorRepository extends MongoRepository<Evaluator, String> {
    Optional<Evaluator> findByUserName(String userName);
}
