package com.bilport.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.Report;
import com.bilport.demo.domain.model.ReportFile;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.repository.ReportFileRepository;
import com.bilport.demo.repository.ReportRepository;
import com.bilport.demo.repository.StudentRepository;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ReportFileRepository reportFileRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<Report> getReports() {
        return reportRepository.findAll();
    }

    public Report findByReportId(String reportId) {
        return reportRepository.findById(reportId).orElse(null);
    }

    public Report findByReportOwnerAndVersion(String reportOwner, int version) {
        return reportRepository.findByReportOwnerAndVersion(reportOwner, version).orElse(null);
    }

    public void uploadStudentReport(Report report) {
        reportRepository.save(report);

        Student student = studentRepository.findById(report.reportOwner).orElse(null);

        if (report.course.equals("CS299")) {
            String[] newReports299;

            if (student.getReports299() == null) {
                newReports299 = new String[1];
                newReports299[0] = report.reportId;
            } else {
                newReports299 = new String[student.getReports299().length + 1];
                for (int i = 0; i < student.getReports299().length; i++) {
                    newReports299[i] = student.getReports299()[i];
                }
                newReports299[student.getReports299().length] = report.reportId;
            }

            student.setReports299(newReports299);
        } else if (report.course.equals("CS399")) {
            String[] newReports399;

            if (student.getReports399() == null) {
                newReports399 = new String[1];
                newReports399[0] = report.reportId;
            } else {
                newReports399 = new String[student.getReports399().length + 1];
                for (int i = 0; i < student.getReports399().length; i++) {
                    newReports399[i] = student.getReports399()[i];
                }
                newReports399[student.getReports399().length] = report.reportId;
            }

            student.setReports399(newReports399);
        }

        studentRepository.save(student);

        // incrementReportVersion(report.reportOwner, report.course);
    }

    // public void incrementReportVersion(String studentId, String course) {
    // Student student = studentRepository.findById(studentId).orElse(null);

    // if (course.equals("CS299")) {
    // student.setReportVersionCS299(student.getReportVersionCS299() + 1);
    // } else if (course.equals("CS399")) {
    // student.setReportVersionCS399(student.getReportVersionCS399() + 1);
    // System.out.println("KELLER");

    // }

    // System.out.println("INCCC" + student.getReportVersionCS299() + " " +
    // student.getReportVersionCS399() + course);
    // studentRepository.save(student);
    // }

    public void uploadFileToReport(MultipartFile reportFile, String name) {
        try {
            reportFileRepository.save(new ReportFile(name, reportFile.getBytes(), reportFile.getContentType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] downloadReportFile(String reportId) {
        try {
            return reportFileRepository.findById(reportId).get().getReportFile();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Report> findByReportOwner(String username) {
        try {
            return reportRepository.findByReportOwner(username).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Report> findByReportOwnerAndCourse(String username, String course) {
        try {
            return reportRepository.findByReportOwnerAndCourse(username, course).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
