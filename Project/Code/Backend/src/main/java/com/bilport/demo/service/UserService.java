package com.bilport.demo.service;

import com.bilport.demo.domain.model.User;
import com.bilport.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private final Log logger = LogFactory.getLog(getClass());

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findByUserName(String name) {
        try {
            return userRepository.findById(name).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}