package com.bilport.demo.domain.dto;

import java.util.Date;

public class SubmissionResponse {
    public String reportId;
    public String reportOwner;
    public String reportFileId;
    public String course;
    public Date reportDate;
    public String reportStatus;
    public int version;
    String studentName;
    String studentSurname;
    String studentEmail;

    public SubmissionResponse() {
    }

    public SubmissionResponse(String reportId, String reportOwner, String reportFileId, String course, Date reportDate,
            String reportStatus, int version, String studentName, String studentSurname, String studentEmail) {
        this.reportId = reportId;
        this.reportOwner = reportOwner;
        this.reportFileId = reportFileId;
        this.course = course;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
        this.version = version;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentEmail = studentEmail;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String id) {
        this.reportId = id;
    }

    public String getReportOwner() {
        return reportOwner;
    }

    public void setReportOwner(String owner) {
        this.reportOwner = owner;
    }

    public String getReportFileId() {
        return reportFileId;
    }

    public void setReportFileId(String fileId) {
        this.reportFileId = fileId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date date) {
        this.reportDate = date;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String status) {
        this.reportStatus = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    
}
