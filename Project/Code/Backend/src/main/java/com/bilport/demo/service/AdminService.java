package com.bilport.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilport.demo.domain.dto.SubmissionResponse;
import com.bilport.demo.domain.model.Admin;
import com.bilport.demo.domain.model.Report;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.repository.AdminRepository;
import com.bilport.demo.repository.ReportRepository;
import com.bilport.demo.repository.StudentRepository;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ReportRepository  reportRepository;

    public Admin findById(String userName) {
        return adminRepository.findById(userName).orElse(null);
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public List<SubmissionResponse> getSubmissions() {
        List<Student> assignedStudents = studentRepository.findAll();
        ArrayList<SubmissionResponse> submissions = new ArrayList<SubmissionResponse>();

        for (Student assignedStudent : assignedStudents) {
            ArrayList<Report> reports = new ArrayList<Report>();
            reportRepository.findByReportOwner(assignedStudent.getUserName()).get().forEach(reports::add);

            for (Report report : reports) {
                SubmissionResponse submissionResponse = new SubmissionResponse();

                submissionResponse.setReportId(report.getReportId());
                submissionResponse.setReportOwner(report.getReportOwner());
                submissionResponse.setReportFileId(report.getReportFileId());
                submissionResponse.setCourse(report.getCourse());
                submissionResponse.setReportDate(report.getReportDate());
                submissionResponse.setReportStatus(report.getReportStatus());
                submissionResponse.setVersion(report.getVersion());
                submissionResponse.setStudentName(assignedStudent.getStudentName());
                submissionResponse.setStudentSurname(assignedStudent.getStudentSurname());
                submissionResponse.setStudentEmail(assignedStudent.getStudentEmail());

                submissions.add(submissionResponse);
            }
        }
        return submissions;
    }
}
