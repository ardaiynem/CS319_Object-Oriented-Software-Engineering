package com.bilport.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilport.demo.domain.dto.StudentResponse;
import com.bilport.demo.domain.dto.SubmissionResponse;
import com.bilport.demo.domain.model.TA;
import com.bilport.demo.service.TAService;

@RestController
@RequestMapping(value = "/TAs")
public class TAController {
    @Autowired
    TAService taService;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public TA getTA(@PathVariable("id") String id) {
        return taService.findById(id);
    }

    // @ResponseBody
    // @GetMapping(value = "/submissions/{id}")
    // public List<SubmissionResponse> getSubmissions(@PathVariable("id") String id) {
    //     return taService.getSubmissions(id);
    // }

    // @ResponseBody
    // @GetMapping(value = "/students/{id}")
    // public List<StudentResponse> getAssignedStudents(@PathVariable("id") String id) {
    //     return taService.getAssignedStudents(id);
    // }
}
