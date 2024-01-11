package com.bilport.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilport.demo.domain.dto.SubmissionResponse;
import com.bilport.demo.domain.model.Admin;
import com.bilport.demo.service.AdminService;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Admin getadmin(@PathVariable("id") String id) {
        return adminService.findById(id);
    }

    // // @GetMapping(value = "/{id}")
    // // public List<Student> getAllStudents(@PathVariable("id") String id) {
    // public List<Student> getAllStudents() {
    //     return studentService.getStudents();
    // }

    // // public List<TA> getAllTAs(@PathVariable("id") String id) {
    // public List<TA> getAllTAs() {
    //     return taService.getTAs();
    // }

    // // public List<Evaluator> getAllTAs(@PathVariable("id") String id) {
    // public List<Evaluator> getAllEvaluators() {
    //     return evaluatorService.getEvaluators();
    // }

    @ResponseBody
    @GetMapping(value = "/submissions")
    public List<SubmissionResponse> getSubmissions() {
        return adminService.getSubmissions();
    }
}
