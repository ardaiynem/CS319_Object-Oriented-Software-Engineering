package com.bilport.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilport.demo.domain.model.Superadmin;
import com.bilport.demo.repository.SuperadminRepository;

@Service
public class SuperadminService {
    @Autowired
    SuperadminRepository superadminRepository;

    public Superadmin findById(String userName) {
        return superadminRepository.findById(userName).orElse(null);
    }

    public List<Superadmin> getSuperadmins() {
        return superadminRepository.findAll();
    }

    public void createAdmin(Superadmin superadmin) {
        superadminRepository.save(superadmin);
    }
}
