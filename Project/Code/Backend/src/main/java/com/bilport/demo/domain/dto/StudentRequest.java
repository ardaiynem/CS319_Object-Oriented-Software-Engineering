package com.bilport.demo.domain.dto;


public class StudentRequest {
    String userName;
    String userPassword;
    String studentName;
    String studentSurname;
    String studentEmail;
    String assignedEvaluatorId;
    String assignedTaId;
    String assignedSupervisorId;
    int reportVersionCS299;
    int reportVersionCS399;
    String[] coursesTaken;

    public StudentRequest() {
    }

    public StudentRequest(String userName, String userPassword, String studentName, String studentSurname, String studentEmail, String assignedEvaluatorId, String assignedTaId, String assignedSupervisorId, String[] coursesTaken) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentEmail = studentEmail;
        this.assignedEvaluatorId = assignedEvaluatorId;
        this.assignedTaId = assignedTaId;
        this.assignedSupervisorId = assignedSupervisorId;
        this.coursesTaken = coursesTaken;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getAssignedEvaluatorId() {
        return assignedEvaluatorId;
    }

    public String getAssignedTaId() {
        return assignedTaId;
    }

    public String getAssignedSupervisorId() {
        return assignedSupervisorId;
    }

    public String[] getCoursesTaken() {
        return coursesTaken;
    }

}
