package com.cod.configuration.security;

import com.cod.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    // Request로 들어오는 Jwt Token의 유효성을 검증하는 filter를 filterChain에 등록합니다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = jwtService.getAccessToken();
        if (token != null && !token.equals("")) { // token 검증
            Authentication auth = jwtService.getAuthentication(token); // 인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
        }
        chain.doFilter(request, response);
    }
}