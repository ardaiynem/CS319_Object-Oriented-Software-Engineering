package com.bilport.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilport.demo.domain.model.Supervisor;
import com.bilport.demo.service.SupervisorService;

@RestController
@RequestMapping(value = "/supervisors")
public class SupervisorController {

    @Autowired
    SupervisorService supervisorService;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Supervisor getSupervisorOfStudent(@PathVariable("id") String id) {
        return supervisorService.findByStudentId(id);
    }

    @ResponseBody
    @GetMapping(value = "/direct/{id}")
    public Supervisor getSupervisor(@PathVariable("id") String id) {
        return supervisorService.findById(id);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<String> uploadSupervisorInfo(@PathVariable("id") String id,
            @RequestBody Supervisor supervisor) {
        try {
            supervisorService.createSupervisor(supervisor);

            return new ResponseEntity<String>("Success Upload", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Fail Upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/set/{studentId}")
    public ResponseEntity<String> newSupervisor(@PathVariable("studentId") String studentId,
            @RequestBody Supervisor supervisor) {
        try {
            supervisorService.newSupervisor(studentId, supervisor);

            return new ResponseEntity<String>("Success Upload", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Fail Upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
