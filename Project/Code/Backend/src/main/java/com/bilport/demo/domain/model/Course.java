package com.bilport.demo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    @Id
    String courseCode;
    String courseName;
    String courseTaId;

    public Course() {
    }
    
    public Course(String courseCode, String courseName, String courseTaId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseTaId = courseTaId;
    }

    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseTaId() {
        return courseTaId;
    }
    public void setCourseTaId(String courseTaId) {
        this.courseTaId = courseTaId;
    }

    
}
