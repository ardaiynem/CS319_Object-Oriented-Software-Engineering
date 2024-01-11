package com.bilport.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.SupervisorForm;

@Repository
public interface SupervisorFormRepository extends MongoRepository<SupervisorForm, String> {
    Optional<SupervisorForm> findByStudentId(String reportOwner);

}
