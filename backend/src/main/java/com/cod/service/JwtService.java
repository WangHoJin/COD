package com.cod.service;

import com.cod.entity.User;

public interface JwtService {
    <T> String createAccessToken(int userId);
    String getAccessToken();
    int getUserId();
    User getUser();
}
