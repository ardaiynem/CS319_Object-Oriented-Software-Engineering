package com.bilport.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.EvaluatorForm;
import com.bilport.demo.service.EvaluatorFormService;

@RestController
@RequestMapping(value = "/evaluatorForms")
public class EvaluatorFormController {

    @Autowired
    EvaluatorFormService evaluatorFormService;

    @ResponseBody
    @GetMapping(value = "/{name}")
    public EvaluatorForm getEvaluatorReportOfStudent(@PathVariable("name") String name) {
        return evaluatorFormService.findByStudentId(name);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<String> uploadSupervisorInfo(@PathVariable("id") String id,
            @RequestBody EvaluatorForm evaluatorForm) {
        try {
            evaluatorFormService.createEvaluatorForm(evaluatorForm);

            return new ResponseEntity<String>("Success Upload", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Fail Upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
