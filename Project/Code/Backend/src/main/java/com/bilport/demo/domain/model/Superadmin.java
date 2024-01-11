package com.bilport.demo.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "superadmins")
public class Superadmin extends User {
    String superadminName;
    String superadminSurname;
    String superadminEmail;
    List<Student> allStudents;
    List<Evaluator> allEvaluators;
    List<TA> allTAs;

    public Superadmin() {
    }

    public Superadmin(String userName, String userPassword, List<GrantedAuthority> userAuthorities,
            String superadminName,
            String superadminSurname, String superadminEmail) {
        this.superadminName = superadminName;
        this.superadminSurname = superadminSurname;
        this.superadminEmail = superadminEmail;

        allEvaluators = new ArrayList<Evaluator>();
        allStudents = new ArrayList<Student>();
        allTAs = new ArrayList<TA>();
    }

    public Superadmin(String superadminName, String superadminSurname, String superadminEmail) {
        this.superadminName = superadminName;
        this.superadminSurname = superadminSurname;
        this.superadminEmail = superadminEmail;

        allEvaluators = new ArrayList<Evaluator>();
        allStudents = new ArrayList<Student>();
        allTAs = new ArrayList<TA>();
    }

    public String getSuperadminName() {
        return superadminName;
    }

    public void setSuperadminName(String adminName) {
        this.superadminName = adminName;
    }

    public String getSuperadminSurname() {
        return superadminSurname;
    }

    public void setSuperadminSurname(String adminSurname) {
        this.superadminSurname = adminSurname;
    }

    public String getSuperadminEmail() {
        return superadminEmail;
    }

    public void setSuperadminEmail(String adminEmail) {
        this.superadminEmail = adminEmail;
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
