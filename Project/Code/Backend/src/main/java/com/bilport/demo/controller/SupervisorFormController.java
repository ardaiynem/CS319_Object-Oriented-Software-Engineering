package com.bilport.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.SupervisorForm;
import com.bilport.demo.service.SupervisorFormService;

@RestController
@RequestMapping(value = "/supervisorForms")
public class SupervisorFormController {

    @Autowired
    SupervisorFormService supervisorFormService;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public SupervisorForm getEvaluatorReportOfStudent(@PathVariable("id") String name) {
        return supervisorFormService.findByStudentId(name);
    }

    @ResponseBody
    @GetMapping(value = "/direct/{id}")
    public SupervisorForm getEvaluatorReport(@PathVariable("id") String name) {
        System.out.println("RRRR");
        return supervisorFormService.findById(name);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<String> uploadSupervisorInfo(@PathVariable("id") String id,
            @RequestBody SupervisorForm evaluatorForm) {
        try {
            supervisorFormService.createSupervisorForm(evaluatorForm);

            return new ResponseEntity<String>("Success Upload", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Fail Upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
