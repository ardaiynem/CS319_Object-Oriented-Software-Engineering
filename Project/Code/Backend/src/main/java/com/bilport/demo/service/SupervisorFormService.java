package com.bilport.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.SupervisorForm;
import com.bilport.demo.repository.SupervisorFormRepository;

@Service
public class SupervisorFormService {

    @Autowired
    SupervisorFormRepository supervisorFormRepository;

    public List<SupervisorForm> getForms() {
        return supervisorFormRepository.findAll();
    }

    public SupervisorForm findByStudentId(String reportId) {
        return supervisorFormRepository.findByStudentId(reportId).orElse(null);
    }

    public void createSupervisorForm(SupervisorForm form) {
        supervisorFormRepository.save(form);
    }

    public SupervisorForm findById(String name) {
        System.out.println(supervisorFormRepository.findById(name).orElse(null).getCourse() + ": QQQ33");
        return supervisorFormRepository.findById(name).orElse(null);
    }
}
