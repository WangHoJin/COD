package com.cod.serviceImpl;

import com.cod.configuration.security.CustomUserDetailsService;
import com.cod.dao.UserRepository;
import com.cod.entity.User;
import com.cod.service.JwtService;
import io.jsonwebtoken.*;
import com.cod.configuration.ValidationCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.message.AuthException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.cod.configuration.ConstantConfig.*;
import static com.cod.response.ResponseStatus.UNAUTHORIZED_TOKEN;

@Slf4j
@Service("JwtService")
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public String createAccessToken(int userId) {
        Date now = new Date();
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + ConstantConfig.VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET_KEY)
                .compact();
    }

    @Override
    public String getAccessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    @Override
    public int getUserId() {
        String accessToken = getAccessToken();
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET_KEY)
                    .parseClaimsJws(accessToken);
            if (accessToken == null) return -1;

            int userId = claims.getBody().get("userId", Integer.class);
            if (!ValidationCheck.isValidId(userId)) return -3;

            return userId;
        } catch (Exception exception) {
            return -1;
        }
    }

    @Override
    public User getUser() {
        String accessToken = getAccessToken();
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET_KEY).parseClaimsJws(accessToken);
            if (accessToken == null)
                return null;

            int userId = claims.getBody().get("userId", Integer.class);
            if (!ValidationCheck.isValidId(userId))
                return null;

            User user = userRepository.findById(userId).orElse(null);
            if (user == null)
                return null;
            return user;
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    // 인증 성공시 SecurityContextHolder에 저장할 Authentication 객체 생성
    public Authentication getAuthentication(String token) throws AuthenticationException{
        try{
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getUser().getEmail());
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException(UNAUTHORIZED_TOKEN.getMessage());
        }
    }

    @Override
    // Jwt Token의 유효성 및 만료 기간 검사
    public boolean validateToken(String jwtToken) {
        return this.getClaims(jwtToken) != null;
    }

    @Override
    public Jws<Claims> getClaims(String jwtToken) {
        try {
            return Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET_KEY).parseClaimsJws(jwtToken);
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
            throw ex;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw ex;
        }
        // catch (ExpiredJwtException ex) {
        // log.error("Expired JWT token");
        // throw ex;
        // }
        catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw ex;
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw ex;
        }
    }
}
