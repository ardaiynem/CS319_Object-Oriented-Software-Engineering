package com.bilport.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findByCourseTaId(String taId);
}
