package com.bilport.demo.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilport.demo.domain.model.RefreshToken;
import com.bilport.demo.repository.RefreshTokenRepository;
import com.bilport.demo.util.SecurityConstants;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findById(token);
    }

    public RefreshToken createRefreshToken(String username, List<String> roles) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUsername(username);
        refreshToken.setExpiryDate(Instant.now().plusMillis(SecurityConstants.REFRESH_EXPIRATION_TIME));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setRoles(roles);

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            return null;
        }

        return token;
    }

    @Transactional
    public Long deleteByUserId(String username) {
        return refreshTokenRepository.deleteByUsername(username);
    }
}
