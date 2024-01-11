package com.bilport.demo.domain.dto;

public class EvaluatorRequest {
    String userName;
    String userPassword;
    String evaluatorName;
    String evaluatorSurname;
    String evaluatorEmail;
    int studentLimit;

    public EvaluatorRequest() {
    }

    public EvaluatorRequest(String userName, String userPassword, String evaluatorName, String evaluatorSurname,
            String evaluatorEmail, int studentLimit) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.evaluatorName = evaluatorName;
        this.evaluatorSurname = evaluatorSurname;
        this.evaluatorEmail = evaluatorEmail;
        this.studentLimit = studentLimit;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getEvaluatorName() {
        return evaluatorName;
    }

    public String getEvaluatorSurname() {
        return evaluatorSurname;
    }

    public String getEvaluatorEmail() {
        return evaluatorEmail;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    
}
