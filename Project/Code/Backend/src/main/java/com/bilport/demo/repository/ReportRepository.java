package com.bilport.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilport.demo.domain.model.Report;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    Optional<List<Report>> findByReportOwner(String reportOwner);
    Optional<Report> findByReportOwnerAndVersion(String reportOwner, int version);
    Optional<List<Report>> findByReportOwnerAndCourse(String reportOwner, String course);
}
