package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dto.user.signin.SignInInput;
import com.cod.dto.user.signup.SignUpInput;
import com.cod.entity.UserDB;
import com.cod.response.Response;
import com.cod.service.JwtService;
import com.cod.service.UserService;
import com.cod.dao.UserRepository;
import com.cod.dto.user.signin.SignInOutput;
import com.cod.dto.user.signup.SignUpOutput;

import com.cod.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public Response<SignInOutput> signIn(SignInInput signInInput) {
        // 1. 값 형식 체크
        if (signInInput == null) return new Response<>(ResponseStatus.NO_VALUES);
        if (!ValidationCheck.isValid(signInInput.getEmail()))    return new Response<>(ResponseStatus.BAD_EMAIL_VALUE);
        if (!ValidationCheck.isValid(signInInput.getPassword())) return new Response<>(ResponseStatus.BAD_PASSWORD_VALUE);

        // 2. user 정보 가져오기
        UserDB userDB;
        try {
            String email = signInInput.getEmail();
            String password = signInInput.getPassword();
            List<UserDB> userDBs = userRepository.findByEmail(email);
            if (userDBs.size() == 0) {
                return new Response<>(ResponseStatus.NOT_FOUND_USER);
            } else if (!userDBs.get(0).getPassword().equals(password)) {
                return new Response<>(ResponseStatus.FAILED_TO_SIGN_IN);
            } else {
                userDB = userDBs.get(0);
            }
        } catch (Exception e) {
            return new Response<>(ResponseStatus.DATABASE_ERROR);
        }

        // 3. access token 생성
        String accessToken;
        try{
            accessToken = jwtService.createAccessToken(userDB.getId());
            if (accessToken.isEmpty()) {
                return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
            }
        } catch (Exception e) {
            return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
        }

        // 4. 결과 return
        SignInOutput signInOutput = new SignInOutput(userDB.getId(), accessToken);
        return new Response<>(signInOutput, ResponseStatus.SUCCESS_SIGN_IN);
    }

    @Override
    public Response<SignUpOutput> signUp(SignUpInput signUpInput) {
        // 1. 값 형식 체크
        if (signUpInput == null) return new Response<>(ResponseStatus.NO_VALUES);
        if (!ValidationCheck.isValid(signUpInput.getEmail()))    return new Response<>(ResponseStatus.BAD_EMAIL_VALUE);
        if (!ValidationCheck.isValid(signUpInput.getPassword())) return new Response<>(ResponseStatus.BAD_PASSWORD_VALUE);
        if (!ValidationCheck.isValid(signUpInput.getNickname()))     return new Response<>(ResponseStatus.BAD_NAME_VALUE);

        // 2. 유저 생성
        UserDB userDB = UserDB.builder()
                .email(signUpInput.getEmail())
                .password(signUpInput.getPassword())
                .nickname(signUpInput.getNickname())
                .build();

        try {
            String email = signUpInput.getEmail();
            List<UserDB> existUsers = userRepository.findByEmail(email);
            if (existUsers.size() > 0) {
                return new Response<>(ResponseStatus.EXISTS_EMAIL);
            } else {
                userRepository.save(userDB);
            }
        } catch (Exception e) {
            return new Response<>(ResponseStatus.DATABASE_ERROR);
        }

        // 3. 토큰 생성
        String accessToken;
        try {
            accessToken = jwtService.createAccessToken(userDB.getId());
            if (accessToken.isEmpty()) {
                return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
            }
        } catch (Exception exception) {
            return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
        }

        // 4. 결과 return
        SignUpOutput signUpOutput = new SignUpOutput(userDB.getId(), accessToken);
        return new Response<>(signUpOutput, ResponseStatus.CREATED_USER);
    }
}
