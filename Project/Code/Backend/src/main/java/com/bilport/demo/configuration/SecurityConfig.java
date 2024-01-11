package com.bilport.demo.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomRequestHeaderTokenFilter customFilter() throws Exception {
        return new CustomRequestHeaderTokenFilter(authManager());
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authEx) -> {
                    // response.setHeader("WWW-Authenticate", "Basic realm=\"Access to /signin
                    // authentication endpoint\"");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter()
                            .write("{ \"Error\": \"" + authEx.getMessage() + " - You are not authenticated.\" }");
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                                // .requestMatchers(HttpMethod.POST, "/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/auth/signin").authenticated()
                                .requestMatchers(HttpMethod.GET, "/auth/signout").authenticated()
                                .requestMatchers(HttpMethod.GET, "/auth/refresh").permitAll()
                                .requestMatchers(HttpMethod.GET, "/items").permitAll())

                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/users").hasRole("ADMIN")
                        .requestMatchers("/reports/**").hasAnyRole("STUDENT", "EVALUATOR", "TA")
                        .requestMatchers("/students/**").hasAnyRole("ADMIN", "STUDENT", "EVALUATOR", "TA", "SUPERADMIN")
                        .requestMatchers("/evaluators/**").hasAnyRole("STUDENT", "EVALUATOR", "ADMIN", "SUPERADMIN")
                        .requestMatchers("/admins/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/supervisors/**")
                        .hasAnyRole("STUDENT", "EVALUATOR", "ADMIN", "SUPERADMIN", "SUPERVISOR", "TA")
                        .requestMatchers("/courses/**")
                        .hasAnyRole("STUDENT", "EVALUATOR", "ADMIN", "SUPERADMIN", "SUPERVISOR", "TA")
                        .requestMatchers("/supervisorForms/**")
                        .hasAnyRole("STUDENT", "EVALUATOR", "ADMIN", "SUPERADMIN", "SUPERVISOR", "TA")
                        .requestMatchers("/feedbacks/**")
                        .hasAnyRole("STUDENT", "EVALUATOR", "ADMIN", "SUPERADMIN", "SUPERVISOR", "TA")

                        // .requestMatchers("/items").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated());
        http.addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        List<String> origins = new ArrayList<String>();
        origins.add("http://localhost:5173");
        origins.add("http://localhost:8080");
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(origins);
        config.addAllowedHeader("*");
        config.addExposedHeader("Authorization");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
