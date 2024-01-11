package com.bilport.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilport.demo.domain.dto.StudentResponse;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student findById(String userName) {
        return studentRepository.findById(userName).orElse(null);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public List<StudentResponse> findByCourse(String course) {
        List<Student> students = studentRepository.findAll();
        ArrayList<StudentResponse> studentResponses = new ArrayList<StudentResponse>();

        for (Student student : students) {
            
            for(String courseOfStudent: student.getCoursesTaken()){
                if(courseOfStudent.equals(course)){
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

                    break;
                }
            }
        }

        return studentResponses;
    }
}
