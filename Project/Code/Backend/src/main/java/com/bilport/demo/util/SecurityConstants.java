package com.bilport.demo.util;

public class SecurityConstants {

    public static final String JWT_SECRET = "t3pCSx2wx1ExbQ5z43XXB8my/KR24aon4EH/niU9iZi1I3S69rk1QhlMFFsTrZIY";
    public static final String JWT_COOKIE_NAME = "bilportCookie";
    //public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final long REFRESH_EXPIRATION_TIME = 6_000_000; // 100 mins
    //public static final long EXPIRATION_TIME = 3_600_000;// 1 hour
    public static final long ACCESS_EXPIRATION_TIME = 15_000_000; // 15 sec
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String BASIC_TOKEN_PREFIX =  "Basic ";
    public static final String AUTH_HEADER = "Authorization";

    public static final String SIGN_IN_URI_ENDING = "/signin";

}