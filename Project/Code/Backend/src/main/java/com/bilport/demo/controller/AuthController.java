package com.bilport.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.bilport.demo.domain.dto.AuthResponse;
import com.bilport.demo.domain.model.RefreshToken;
import com.bilport.demo.service.RefreshTokenService;
import com.bilport.demo.service.UserDetailsServiceImpl;
import com.bilport.demo.util.SecurityConstants;
import com.bilport.demo.util.TokenUtils;

@RestController
@RequestMapping
public class AuthController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @GetMapping(value = "/auth/signin")
    @ResponseBody
    public ResponseEntity<AuthResponse> signIn(HttpServletResponse response, HttpServletRequest request) {
        try {
            AuthResponse authResponse = new AuthResponse();
            String uname = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            UserDetails user = userDetailsService.loadUserByUsername(uname);

            logger.info("Principal/Username obtained from SecurityContextHolder: " + uname);
            logger.info(
                    "Is Authenticated? : " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

            String token = TokenUtils.generateJWTAccessToken(uname);
            logger.info("Generated JWT Token: " + token);

            List<String> roleList = user.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            authResponse.setAccessToken(token);
            authResponse.setUsername(uname);
            authResponse.setRoles(roleList);

            logger.info(authResponse.getAccessToken());
            logger.info(roleList);

            response.addHeader(SecurityConstants.AUTH_HEADER, SecurityConstants.BEARER_TOKEN_PREFIX + token);

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername(), roleList);
            ResponseCookie jwtRefreshCookie = TokenUtils.generateRefreshJwtCookie(refreshToken.getToken());

            response.addHeader(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());
            return new ResponseEntity<>(authResponse, HttpStatus.OK);

        } catch (Exception ex) {
            logger.info("Exception error: " + ex.getMessage());

            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/auth/signup")
    public ResponseEntity<String> signUp() {
        String msg = "Ok! You have been Registered! - Now you can Login: ";

        try {

            return new ResponseEntity<>(msg, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/auth/signout")
    public ResponseEntity<String> signOut(HttpServletResponse response) {

        try {

            String uname = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (uname != "anonymousUser") {
                refreshTokenService.deleteByUserId(uname);
            }

            ResponseCookie jwtRefreshCookie = TokenUtils.getCleanJwtRefreshCookie();

            response.addHeader(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

            return new ResponseEntity<>("Signed out: ", HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/auth/refresh")
    public ResponseEntity<AuthResponse> refreshtoken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = TokenUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {

            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(token -> {
                        String accessToken = TokenUtils.generateJWTAccessToken(token.getUsername());

                        AuthResponse authResponse = new AuthResponse();
                        authResponse.setAccessToken(accessToken);
                        authResponse.setRoles(token.getRoles());
                        authResponse.setUsername(token.getUsername());

                        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
                    }).orElseGet(() -> {
                        ResponseCookie jwtRefreshCookie = TokenUtils.getCleanJwtRefreshCookie();
                            response.addHeader(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());
                            return new ResponseEntity<AuthResponse>(new AuthResponse(), HttpStatus.UNAUTHORIZED);});
        }

        return new ResponseEntity<>(new AuthResponse(), HttpStatus.BAD_REQUEST);
    }

}