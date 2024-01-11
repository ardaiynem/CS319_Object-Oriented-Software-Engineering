package com.bilport.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilport.demo.domain.dto.EvaluatorRequest;
import com.bilport.demo.domain.dto.StudentResponse;
import com.bilport.demo.domain.dto.SubmissionResponse;
import com.bilport.demo.domain.model.Evaluator;
import com.bilport.demo.domain.model.Report;
import com.bilport.demo.service.EvaluatorService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/evaluators")
public class EvaluatorController {
    @Autowired
    EvaluatorService evaluatorService;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Evaluator getEvaluator(@PathVariable("id") String id) {
        return evaluatorService.findById(id);
    }

    @ResponseBody
    @GetMapping(value = "/students/{id}")
    public List<StudentResponse> getAssignedStudents(@PathVariable("id") String id) {
        return evaluatorService.getAssignedStudents(id);
    }

    @ResponseBody
    @GetMapping(value = "/reports/{id}")
    public List<Report> getAssignedReports(@PathVariable("id") String id) {
        return evaluatorService.getAssignedReports(id);
    }

    @ResponseBody
    @GetMapping
    public List<Evaluator> getEvaluators() {
        return evaluatorService.getEvaluators();
    }

    @ResponseBody
    @GetMapping(value = "/submissions/{id}")
    public List<SubmissionResponse> getSubmissions(@PathVariable("id") String id) {
        return evaluatorService.getSubmissions(id);
    }

    @PostMapping(value = "/newEvaluator")
    public String createEvaluator(@RequestBody EvaluatorRequest evaluatorReq) {
        Evaluator evaluator = new Evaluator();
        evaluator.setUserName(evaluatorReq.getUserName());
        evaluator.setUserPassword(evaluatorReq.getUserPassword());
        evaluator.setEvaluatorName(evaluatorReq.getEvaluatorName());
        evaluator.setEvaluatorSurname(evaluatorReq.getEvaluatorSurname());
        evaluator.setEvaluatorEmail(evaluatorReq.getEvaluatorEmail());
        evaluator.setStudentLimit(evaluatorReq.getStudentLimit());
        evaluator.setUserAuthorities(new ArrayList<GrantedAuthority>() {
            {
                add(new SimpleGrantedAuthority("ROLE_EVALUATOR"));
            }
        });

        evaluatorService.createEvaluator(evaluator);
        return "Evaluator created successfully";
    }

    @ResponseBody
    @GetMapping(value = "/assign/{evlauatorId}/{studentId}")
    public String assignEvalToStudent(@PathVariable("evlauatorId") String evlauatorId,
            @PathVariable("studentId") String studentId) {
        evaluatorService.assignEvalToStudent(evlauatorId, studentId);
        return "Evaluator assigned successfully";
    }

    @GetMapping(value = "/change/{evlauatorId}/{studentLimit}")
    public String getMethodName(@PathVariable("evlauatorId") String evlauatorId,
            @PathVariable("studentLimit") int studentLimit) {
        try {
            evaluatorService.changeStudentLimit(evlauatorId, studentLimit);
            System.out.println("Student limit changed successfully");
            return "Student limit changed successfully";
        } catch (Exception e) {
            System.out.println("AAAAA NOOO");
            return "Error occured";
        }
    }

}
