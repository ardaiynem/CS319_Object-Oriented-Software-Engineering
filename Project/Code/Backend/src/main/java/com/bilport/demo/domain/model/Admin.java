package com.bilport.demo.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "admins")
public class Admin extends User {
    String adminName;
    String adminSurname;
    String adminEmail;
    List<Student> allStudents;
    List<Evaluator> allEvaluators;
    List<TA> allTAs;

    public Admin() {
    }

    public Admin(String userName, String userPassword, List<GrantedAuthority> userAuthorities, String adminName,
            String adminSurname, String adminEmail) {
        super(userName, userPassword, userAuthorities);
        this.adminName = adminName;
        this.adminSurname = adminSurname;
        this.adminEmail = adminEmail;

        allEvaluators = new ArrayList<Evaluator>();
        allStudents = new ArrayList<Student>();
        allTAs = new ArrayList<TA>();
    }

    public Admin(String adminName, String adminSurname, String adminEmail) {
        this.adminName = adminName;
        this.adminSurname = adminSurname;
        this.adminEmail = adminEmail;

        allEvaluators = new ArrayList<Evaluator>();
        allStudents = new ArrayList<Student>();
        allTAs = new ArrayList<TA>();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminSurname() {
        return adminSurname;
    }

    public void setAdminSurname(String adminSurname) {
        this.adminSurname = adminSurname;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public List<Evaluator> getAllEvaluators() {
        return allEvaluators;
    }

    public void setAllEvaluators(List<Evaluator> allEvaluators) {
        this.allEvaluators = allEvaluators;
    }

    public List<TA> getAllTAs() {
        return allTAs;
    }

    public void setAllTAs(List<TA> allTAs) {
        this.allTAs = allTAs;
    }
}
