package com.bilport.demo.domain.dto;

public class StudentResponse {
    String studentName;
    String studentSurname;
    String studentEmail;
    String assignedEvaluatorId;
    String assignedSupervisorId;
    String studentId;
    String[] reports299;
    String[] reports399;
    String[] coursesTaken;

    public StudentResponse() {
    }

    public StudentResponse(String studentName, String studentSurname, String studentEmail, String assignedEvaluatorId,
            String assignedSupervisorId, String studentId, String[] reports299, String[] reports399,
            String[] coursesTaken) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentEmail = studentEmail;
        this.assignedEvaluatorId = assignedEvaluatorId;
        this.assignedSupervisorId = assignedSupervisorId;
        this.studentId = studentId;
        this.reports299 = reports299;
        this.reports399 = reports399;
        this.coursesTaken = coursesTaken;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String surname) {
        this.studentSurname = surname;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String email) {
        this.studentEmail = email;
    }

    public String getAssignedEvaluatorId() {
        return assignedEvaluatorId;
    }

    public void setAssignedEvaluatorId(String assignedEvaluatorId) {
        this.assignedEvaluatorId = assignedEvaluatorId;
    }

    public String getAssignedSupervisorId() {
        return assignedSupervisorId;
    }

    public void setAssignedSupervisorId(String assignedSupervisorId) {
        this.assignedSupervisorId = assignedSupervisorId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String[] getReports299() {
        return reports299;
    }

    public void setReports299(String[] reports299) {
        this.reports299 = reports299;
    }

    public String[] getReports399() {
        return reports399;
    }

    public void setReports399(String[] reports399) {
        this.reports399 = reports399;
    }

    public String[] getCoursesTaken() {
        return coursesTaken;
    }

    public void setCoursesTaken(String[] coursesTaken) {
        this.coursesTaken = coursesTaken;
    }

}
