package com.bilport.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.bilport.demo.domain.model.Course;
import com.bilport.demo.domain.model.TA;
import com.bilport.demo.repository.CourseRepository;
import com.bilport.demo.repository.TARepository;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TARepository taRepository;

    public Course findByCourseCode(String courseCode) {
        return courseRepository.findById(courseCode).orElse(null);
    }

    public Course findByTaId(String taId) {
        return courseRepository.findByCourseTaId(taId).orElse(null);
    }

    public TA findTaOfCourse(String courseCode) {
        Course course = courseRepository.findById(courseCode).orElse(null);

        if (course == null) {
            return null;
        }

        return taRepository.findById(course.getCourseTaId()).orElse(null);
    }

    public String setCourseTa(String courseCode, TA newTa) {
        Course course = courseRepository.findById(courseCode).orElse(null);
        TA oldTa = taRepository.findById(course.getCourseTaId()).orElse(null);

        if (course == null || oldTa == null) {
            return "Error";
        } else {
            newTa.setUserAuthorities(new ArrayList<GrantedAuthority>() {
                {
                    add(new SimpleGrantedAuthority("ROLE_TA"));
                }
            });
            taRepository.delete(oldTa);
            taRepository.save(newTa);

            course.setCourseTaId(newTa.getUserName());
            courseRepository.save(course);
        }

        return "Success";
    }
}
