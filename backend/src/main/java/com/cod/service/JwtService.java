package com.cod.service;

public interface JwtService {
    <T> String createAccessToken(int userId);
    String getAccessToken();
    int getUserId();
}
