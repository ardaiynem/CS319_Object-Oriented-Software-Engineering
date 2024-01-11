package com.bilport.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{
    Optional<Student> findByUserName(String userName);
}
