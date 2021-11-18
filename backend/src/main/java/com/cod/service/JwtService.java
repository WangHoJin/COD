package com.cod.service;

import com.cod.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.Authentication;

public interface JwtService {
    <T> String createAccessToken(int userId);
    String getAccessToken();
    int getUserId();
    User getUser();
    Authentication getAuthentication(String token);
    boolean validateToken(String jwtToken);
    Jws<Claims> getClaims(String jwtToken);
}
