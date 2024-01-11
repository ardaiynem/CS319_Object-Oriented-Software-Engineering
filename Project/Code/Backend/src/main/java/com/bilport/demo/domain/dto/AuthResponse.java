package com.bilport.demo.domain.dto;
import java.util.List;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String username;
    private List<String> roles;
}
