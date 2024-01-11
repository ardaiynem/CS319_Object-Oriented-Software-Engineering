package com.bilport.demo.domain.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tokens")
public class RefreshToken {
  private String username;

  private List<String> roles;

  @Id
  private String token;

  private Instant expiryDate;
}