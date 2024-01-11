package com.bilport.demo.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bilport.demo.service.UserDetailsServiceImpl;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Log logger = LogFactory.getLog(getClass());

    
    @Autowired
    UserDetailsServiceImpl userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = null;

        if (authentication == null) {
            return null;
        }
        
        String uname = String.valueOf(authentication.getName());
        String upassw = String.valueOf(authentication.getCredentials());

        logger.info("Username: " + uname + " Password: " + upassw);

        //User is an org.springframework.security.core.userdetails.User object
        User user = (User) userService.loadUserByUsername(uname);

        if (user == null) throw new UsernameNotFoundException(String.format("Username not found")); //throw new UsernameNotFoundException(String.format("Username not found"));
    
        if (user.getPassword().equals(upassw)) {
            authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        }
        
        logger.info(user.getPassword() + " " + upassw);
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
