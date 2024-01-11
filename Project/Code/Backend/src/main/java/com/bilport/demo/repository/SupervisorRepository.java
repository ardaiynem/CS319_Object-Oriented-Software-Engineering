package com.bilport.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.Supervisor;

@Repository
public interface SupervisorRepository extends MongoRepository<Supervisor, String> {
    Optional<Supervisor> findById(String userName);
    Optional<Supervisor> findByAssignedstudentId(String assignedstudentId);
}
