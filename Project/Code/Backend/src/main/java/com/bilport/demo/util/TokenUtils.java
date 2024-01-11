package com.bilport.demo.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtils {
    
    private static Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SecurityConstants.JWT_SECRET),
            SignatureAlgorithm.HS512.getJcaName());

    public static String generateJWTAccessToken(String userName) {

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.ACCESS_EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(hmacKey)
                .compact();
        return token;
    }

    // public static String generateJWTRefreshToken(String userName) {

    //     Date currentDate = new Date();
    //     Date expireDate = new Date(currentDate.getTime() + SecurityConstants.REFRESH_EXPIRATION_TIME);

    //     String token = Jwts.builder()
    //             .setSubject(userName)
    //             .setIssuedAt(new Date())
    //             .setExpiration(expireDate)
    //             .signWith(hmacKey)
    //             .compact();
    //     return token;
    // }

    public static String getJwtFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, "jwtCookie");
    }

    public static String getJwtRefreshFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, "jwtRefreshCookie");
    }

    private static ResponseCookie generateCookie(String name, String value, String path) {
        ResponseCookie cookie = ResponseCookie.from(name, value).path(path).maxAge(24 * 60 * 60).httpOnly(true)
                .sameSite("None").secure(true).build();
        return cookie;
    }

    public static ResponseCookie generateJwtCookie(String userName) {
        String jwt = generateJWTAccessToken(userName);

        return generateCookie("jwtCookie", jwt, "/");
    }

    public static ResponseCookie generateRefreshJwtCookie(String refreshToken) {
        return generateCookie("jwtRefreshCookie", refreshToken, "/auth/refresh");
    }

    public static ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from("jwtCookie", null).path("/").sameSite("None").secure(true).build();
        return cookie;
    }

    public static ResponseCookie getCleanJwtRefreshCookie() {
        ResponseCookie cookie = ResponseCookie.from("jwtRefreshCookie", null).path("/auth/refresh")
                .sameSite("None").secure(true).build();
        return cookie;
    }

    private static String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public static String getUsernameFromJWTUserToken(String token) {

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token);

        return claims.getBody().getSubject();
    }

    public static boolean isJWTTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

    public static String[] decodedBase64(String token) {

        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String pairedCredentials = new String(decodedBytes);
        String[] credentials = pairedCredentials.split(":", 2);

        return credentials;

    }

}
