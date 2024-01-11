package com.bilport.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.Superadmin;

@Repository
public interface SuperadminRepository extends MongoRepository<Superadmin, String> {
    Optional<Superadmin> findByUserName(String userName);
}
