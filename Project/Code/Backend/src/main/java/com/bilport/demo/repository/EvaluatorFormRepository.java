package com.bilport.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.EvaluatorForm;

@Repository
public interface EvaluatorFormRepository extends MongoRepository<EvaluatorForm, String> {
    Optional<EvaluatorForm> findByStudentId(String reportOwner);

}
