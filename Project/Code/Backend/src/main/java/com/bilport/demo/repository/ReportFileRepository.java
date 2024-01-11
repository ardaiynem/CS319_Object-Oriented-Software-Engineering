package com.bilport.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.ReportFile;

@Repository
public interface ReportFileRepository extends MongoRepository<ReportFile, String> {
    
}
