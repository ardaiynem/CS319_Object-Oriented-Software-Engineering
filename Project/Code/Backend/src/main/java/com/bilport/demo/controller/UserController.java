package com.bilport.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @GetMapping(value = "/items")
    public ResponseEntity<String> getAllItems() {

        try {
            return new ResponseEntity<>("Items", HttpStatus.OK);    

        } catch (Exception ex) {
            return new ResponseEntity<>("Items -- exception", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity<String> getAllUsers() {

        try {
            return new ResponseEntity<>("Users", HttpStatus.OK);    

        } catch (Exception ex) {
            return new ResponseEntity<>("Users -- exception", HttpStatus.UNAUTHORIZED);
        }
    }

}