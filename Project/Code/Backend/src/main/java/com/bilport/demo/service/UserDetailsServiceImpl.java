package com.bilport.demo.service;

import com.bilport.demo.domain.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserService repoUser;

    @Autowired
    private StudentService repoStudent;

    @Autowired
    private EvaluatorService repoEvaluator;

    @Autowired
    private SupervisorService repoSupervisor;

    @Autowired
    private TAService repoTA;

    @Autowired
    private AdminService repoAdmin;

    @Autowired
    private SuperadminService repoSuperadmin;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user;
        org.springframework.security.core.userdetails.User springUser = null;

        user = repoUser.findByUserName(userName);

        if (user == null) {
            if (repoStudent.findById(userName) != null)
                user = repoStudent.findById(userName);

            else if (repoEvaluator.findById(userName) != null)
                user = repoEvaluator.findById(userName);

            else if (repoSupervisor.findById(userName) != null)
                user = repoSupervisor.findById(userName);

            else if (repoTA.findById(userName) != null)
                user = repoTA.findById(userName);

            else if (repoAdmin.findById(userName) != null)
                user = repoAdmin.findById(userName);

            else if (repoSuperadmin.findById(userName) != null)
                user = repoSuperadmin.findById(userName);

        }

        if (user != null) {
            springUser = new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    user.getUserAuthorities());
            return springUser;
        } else {
            // throw new UsernameNotFoundException(String.format("Username not found"));
            return null;
        }
        // return null;
    }
}
