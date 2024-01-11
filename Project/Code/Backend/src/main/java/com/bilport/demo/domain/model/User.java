package com.bilport.demo.domain.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import com.bilport.demo.controller.MailController;


@Document(collection = "users")
public class User {
    
    @Id
    private String userName;
    private String userPassword;
    private List<GrantedAuthority> userAuthorities;

    //protected MailController mailer = new MailController();

    public User() {
    }
          
    public User(String userName, String userPassword, List<GrantedAuthority> userAuthorities) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAuthorities = userAuthorities;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<GrantedAuthority> getUserAuthorities() {
        return userAuthorities;
    }
    public void setUserAuthorities(List<GrantedAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

}