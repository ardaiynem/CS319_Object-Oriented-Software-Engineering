package com.bilport.demo.domain.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "TAs")
public class TA extends User {
    String taFullName;
    String taEmail;
    String assignedCourse;

    public TA() {
    }

    public TA(String userName, String userPassword, List<GrantedAuthority> userAuthorities, String taFullName,
            String taEmail, String assignedCourse) {
        super(userName, userPassword, userAuthorities);
        this.taFullName = taFullName;
        this.taEmail = taEmail;
        this.assignedCourse = assignedCourse;
        //sendRegistrationMail();
    }

    public TA(String taFullName, String taEmail) {
        this.taFullName = taFullName;
        this.taEmail = taEmail;
    }

    public String getTaFullName() {
        return taFullName;
    }

    public void setTaFullName(String taFullName) {
        this.taFullName = taFullName;
    }

    public String getTaEmail() {
        return taEmail;
    }

    public void setTaEmail(String taEmail) {
        this.taEmail = taEmail;
    }

    public String getAssignedCourse() {
        return assignedCourse;
    }

    public void setAssignedCourse(String assignedCourse) {
        this.assignedCourse = assignedCourse;
    }
    /*
    public void sendRegistrationMail(){
        String subject = "Bilport 2023 Spring Semester Account Info";
        String body = "Your account as a TA has been created in the Bilport system for 2023 Spring Semester. Your username and password can be found below.<p/>";
        String info = "<p/>Username: " + this.getUserName() + "<p/>Password: " + this.getUserPassword() + "<p/>";
        String signature = "<p/>Bilport Team";
        mailer.sendEmail(taEmail, subject, body + info + signature);
    }
     */
}
