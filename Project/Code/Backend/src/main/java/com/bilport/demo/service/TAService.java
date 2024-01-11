package com.bilport.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilport.demo.domain.dto.StudentResponse;
import com.bilport.demo.domain.dto.SubmissionResponse;
import com.bilport.demo.domain.model.Report;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.domain.model.TA;
import com.bilport.demo.repository.ReportRepository;
import com.bilport.demo.repository.StudentRepository;
import com.bilport.demo.repository.TARepository;

@Service
public class TAService {
    @Autowired
    TARepository taRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ReportRepository reportRepository;


    public TA findById(String userName) {
        return taRepository.findById(userName).orElse(null);
    }

    public List<TA> getTAs() {
        return taRepository.findAll();
    }

    public void createTA(TA ta) {
        taRepository.save(ta);
    }

    // public List<SubmissionResponse> getSubmissions(String id) {
    //     String[] assignedStudents = taRepository.findById(id).get().getAssignedStudents();
    //     ArrayList<SubmissionResponse> submissions = new ArrayList<SubmissionResponse>();

    //     for (String assignedStudent : assignedStudents) {
    //         Student student = studentRepository.findById(assignedStudent).get();

    //         ArrayList<Report> reports = new ArrayList<Report>();
    //         reportRepository.findByReportOwner(assignedStudent).get().forEach(reports::add);

    //         for (Report report : reports) {
    //             SubmissionResponse submissionResponse = new SubmissionResponse();

    //             submissionResponse.setReportId(report.getReportId());
    //             submissionResponse.setReportOwner(report.getReportOwner());
    //             submissionResponse.setReportFileId(report.getReportFileId());
    //             submissionResponse.setCourse(report.getCourse());
    //             submissionResponse.setReportDate(report.getReportDate());
    //             submissionResponse.setReportStatus(report.getReportStatus());
    //             submissionResponse.setVersion(report.getVersion());
    //             submissionResponse.setStudentName(student.getStudentName());
    //             submissionResponse.setStudentSurname(student.getStudentSurname());
    //             submissionResponse.setStudentEmail(student.getStudentEmail());

    //             submissions.add(submissionResponse);
    //         }
    //     }
    //     return submissions;

    // }

    // public List<StudentResponse> getAssignedStudents(String id) {
    //     String[] assignedStudents = taRepository.findById(id).get().getAssignedStudents();
    //     ArrayList<StudentResponse> studentResponses = new ArrayList<StudentResponse>();

    //     for (String assignedStudent : assignedStudents) {
    //         Student student = studentRepository.findById(assignedStudent).get();
    //         StudentResponse studentResponse = new StudentResponse();

    //         studentResponse.setStudentName(student.getStudentName());
    //         studentResponse.setStudentSurname(student.getStudentSurname());
    //         studentResponse.setStudentEmail(student.getStudentEmail());
    //         studentResponse.setAssignedEvaluatorId(student.getAssignedEvaluatorId());
    //         studentResponse.setAssignedSupervisorId(student.getAssignedSupervisorId());
    //         studentResponse.setStudentId(student.getUserName());

    //         studentResponses.add(studentResponse);
    //     }

    //     return studentResponses;
    // }
}
