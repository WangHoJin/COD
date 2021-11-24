package com.cod.controller;

import com.cod.dto.user.jwt.JwtOutput;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.dto.user.profile.ProfileUpdate;
import com.cod.dto.user.search.UserSearchInput;
import com.cod.dto.user.search.UserSearchOutput;
import com.cod.dto.user.signin.SignInInput;
import com.cod.dto.user.signin.SignInOutput;
import com.cod.dto.user.signup.SignUpOutput;
import com.cod.dto.user.signup.SignUpInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.JwtService;
import com.cod.service.UserService;
import com.cod.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    /**
     * 회원가입 API
     * [POST] /users/signup
     *
     * @return Response<SignUpOutput>
     */
    // Body
    @PostMapping("/signup")
    public ResponseEntity<Response<SignUpOutput>> signUp(@RequestBody SignUpInput signUpInput) {
        System.out.println("[POST] /users/signup");
        return userService.signUp(signUpInput);
    }

    /**
     * 로그인 API
     * [POST] /users/signin
     *
     * @return Response<SignInOutput>
     */
    // Body
    @PostMapping("/signin")
    public ResponseEntity<Response<SignInOutput>> signIn(@RequestBody SignInInput signInInput) {
        log.info("[POST] /user/signin");
        return userService.signIn(signInInput);
    }

    /**
     * JWT토큰 검증 API
     * [POST] /users/jwt
     *
     * @return Response<JwtOutput>
     */
    @PostMapping("/jwt")
    public Response<JwtOutput> jwt() {
        log.info("[POST] /user/jwt");
        int userId = jwtService.getUserId();
        if (userId == -1) return new Response<>(ResponseStatus.UNAUTHORIZED_TOKEN);
        if (userId == -2) return new Response<>(ResponseStatus.BAD_ACCESS_TOKEN_VALUE);
        if (userId == -3) return new Response<>(ResponseStatus.FORBIDDEN_USER_ID);
        JwtOutput jwtOutput = new JwtOutput(userId);
        return new Response<>(jwtOutput, ResponseStatus.SUCCESS_SIGN_IN);
    }

    /**
     * 회원탈퇴 API
     * [DELETE] /users/deactivate
     *
     * @return Response<Object>
     */
    @DeleteMapping("/deactivate")
    public ResponseEntity<Response<Object>> deactivate() {
        log.info("[DELETE] /users/deactivate");
        return userService.deactivate();
    }

    /**
     * 프로필 조회 API
     * [POST] /users/profile
     *
     * @return Response<ProfileOutput>
     */
    // Body
    @GetMapping("/profile")
    public ResponseEntity<Response<ProfileOutput>> getProfile() {
        log.info("[POST] /users/profile");
        return userService.getProfile();
    }

    /**
     * 프로필 조회 API
     * [POST] /users/profile
     *
     * @return Response<ProfileOutput>
     */
    // Body
    @GetMapping("/{id}")
    public ResponseEntity<Response<ProfileOutput>> getUser(@PathVariable int id)  {
        log.info("[POST] /users/{id}}");
        return userService.getUser(id);
    }

    /**
     * 프로필 편집 API
     * [PATCH] /users/profile
     *
     * @return Response<Object>
     */
    // Body
    @PatchMapping("/profile")
    public ResponseEntity<Response<Object>> updateProfile(@RequestBody ProfileUpdate profileUpdate) {
        log.info("[PATCH] /users/");
        return userService.updateProfile(profileUpdate);
    }

    /**
     * 유저 검색 API
     * [GET] /users?nickname=
     *
     * @return Response<UserSearchOutput>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<UserSearchOutput>> getUserByNickName(UserSearchInput userSearchInput) {
            return userService.findUserByNickname(userSearchInput);
    }

}
