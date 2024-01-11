package com.bilport.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilport.demo.domain.dto.StudentRequest;
import com.bilport.demo.domain.dto.StudentResponse;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.service.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @ResponseBody
    @GetMapping(value = "/{name}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable("name") String studentId) {
        try {
            StudentResponse studentResponse = new StudentResponse();
            Student studentModel = studentService.findById(studentId);

            studentResponse.setStudentName(studentModel.getStudentName());
            studentResponse.setStudentSurname(studentModel.getStudentSurname());
            studentResponse.setStudentEmail(studentModel.getStudentEmail());
            studentResponse.setAssignedEvaluatorId(studentModel.getAssignedEvaluatorId());
            studentResponse.setAssignedSupervisorId(studentModel.getAssignedSupervisorId());
            studentResponse.setStudentId(studentModel.getUserName());
            studentResponse.setReports299(studentModel.getReports299());
            studentResponse.setReports399(studentModel.getReports399());
            studentResponse.setCoursesTaken(studentModel.getCoursesTaken());

            return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<StudentResponse>(new StudentResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/newStudent")
    public String createStudent(@RequestBody StudentRequest studentReq) {
        try {
            Student student = new Student();
            student.setUserName(studentReq.getUserName());
            student.setUserPassword(studentReq.getUserPassword());
            student.setUserAuthorities(new ArrayList<GrantedAuthority>() {
                {
                    add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                }
            });
            student.setStudentName(studentReq.getStudentName());
            student.setStudentSurname(studentReq.getStudentSurname());
            student.setStudentEmail(studentReq.getStudentEmail());
            student.setAssignedEvaluatorId("");
            student.setAssignedSupervisorId("");
            student.setCoursesTaken(studentReq.getCoursesTaken());

            studentService.createStudent(student);
            return "Student created";
        } catch (Exception e) {
            return "Error creating the student: " + e.toString();
        }
    }

    @GetMapping(value = "/all")
    public List<StudentResponse> getStudents() {
        List<Student> students = studentService.getStudents();
        ArrayList<StudentResponse> studentResponses = new ArrayList<StudentResponse>();

        for (Student student : students) {
            StudentResponse studentResponse = new StudentResponse();

            studentResponse.setStudentName(student.getStudentName());
            studentResponse.setStudentSurname(student.getStudentSurname());
            studentResponse.setStudentEmail(student.getStudentEmail());
            studentResponse.setAssignedEvaluatorId(student.getAssignedEvaluatorId());
            studentResponse.setAssignedSupervisorId(student.getAssignedSupervisorId());
            studentResponse.setStudentId(student.getUserName());
            studentResponse.setReports299(student.getReports299());
            studentResponse.setReports399(student.getReports399());
            studentResponse.setCoursesTaken(student.getCoursesTaken());

            studentResponses.add(studentResponse);
        }

        return studentResponses;
    }

    @GetMapping(value = "/all/{course}")
    public List<StudentResponse> getStudentsByCourse(@PathVariable("course") String course) {
        return studentService.findByCourse(course);
    }
}
