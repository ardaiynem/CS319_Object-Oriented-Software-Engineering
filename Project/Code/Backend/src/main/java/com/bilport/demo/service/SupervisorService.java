package com.bilport.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.bilport.demo.controller.MailController;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.domain.model.Supervisor;
import com.bilport.demo.repository.StudentRepository;
import com.bilport.demo.repository.SupervisorRepository;

@Service
public class SupervisorService {
    @Autowired
    SupervisorRepository supervisorRepository;

    @Autowired
    StudentRepository studentRepository;

    // for mail sending capabilities
    @Autowired
    MailController mailer;

    public Supervisor findByStudentId(String userName) {
        return supervisorRepository.findByAssignedstudentId(userName).orElse(null);
    }

    public Supervisor findById(String userName) {
        return supervisorRepository.findById(userName).orElse(null);
    }

    public List<Supervisor> getSupervisors() {
        return supervisorRepository.findAll();
    }

    public void createSupervisor(Supervisor supervisor) {
        supervisorRepository.save(supervisor);
        mailer.sendRegistrationMailSupervisor(supervisor);
    }

    public void newSupervisor(String studentId, Supervisor supervisor) {
        Supervisor oldSupervisor = supervisorRepository.findByAssignedstudentId(studentId).orElse(null);

        Student student = studentRepository.findById(studentId).orElse(null);
        student.setAssignedSupervisorId(supervisor.getUserName());
        studentRepository.save(student);

        supervisor.setUserAuthorities(new ArrayList<GrantedAuthority>() {
            {
                add(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));
            }
        });

        if (oldSupervisor != null) {
            supervisorRepository.delete(oldSupervisor);
        }
        supervisorRepository.save(supervisor);
        mailer.sendRegistrationMailSupervisor(supervisor);
    }
}
