package com.cod.controller;

import com.cod.dto.user.jwt.JwtOutput;
import com.cod.dto.user.signin.SignInInput;
import com.cod.dto.user.signin.SignInOutput;
import com.cod.dto.user.signup.SignUpOutput;
import com.cod.dto.user.signup.SignUpInput;
import com.cod.response.Response;
import com.cod.service.JwtService;
import com.cod.service.UserService;
import com.cod.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    /**
     * 회원가입 API
     * [POST] /users/signup
     * @return Response<SignUpOutput>
     */
    // Body
    @PostMapping("/signup")
    public Response<SignUpOutput> signUp(@RequestBody SignUpInput signUpInput) {
        System.out.println("[POST] /user/signup");
        return userService.signUp(signUpInput);
    }

    /**
     * 로그인 API
     * [POST] /users/signin
     * @return Response<SignInOutput>
     */
    // Body
    @PostMapping("/signin")
    public Response<SignInOutput> signIn(@RequestBody SignInInput signInInput) {
        System.out.println("[POST] /user/signin");
        return userService.signIn(signInInput);
    }

    @PostMapping("/jwt")
    public Response<JwtOutput> jwt() {
        System.out.println("[POST] /user/jwt");
        int userId = jwtService.getUserId();
        if (userId == -1) return new Response<>(ResponseStatus.UNAUTHORIZED_TOKEN);
        if (userId == -2) return new Response<>(ResponseStatus.BAD_ACCESS_TOKEN_VALUE);
        if (userId == -3) return new Response<>(ResponseStatus.FORBIDDEN_USER_ID);
        JwtOutput jwtOutput = new JwtOutput(userId);
        return new Response<>(jwtOutput, ResponseStatus.SUCCESS_SIGN_IN);
    }
}
