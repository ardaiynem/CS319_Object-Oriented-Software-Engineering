package com.bilport.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilport.demo.domain.model.Course;
import com.bilport.demo.domain.model.TA;
import com.bilport.demo.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/courses")
public class CourseController {
    
    @Autowired
    CourseService courseService;

    @GetMapping(value="/{courseCode}")
    public Course getCourse(@PathVariable String courseCode) {
        return courseService.findByCourseCode(courseCode);
    }

    @GetMapping(value="/ta/{courseCode}")
    public TA getTaOfCourse(@PathVariable String courseCode) {
        return courseService.findTaOfCourse(courseCode);
    }
    
    @GetMapping(value="/{taId}")
    public Course getCourseByTa(@PathVariable String taId) {
        return courseService.findByTaId(taId);
    }

    @PostMapping(value="/set/{courseCode}")
    public String setCourseTa(@PathVariable String courseCode, @RequestBody TA ta) {
        return courseService.setCourseTa(courseCode, ta);
    }
}
