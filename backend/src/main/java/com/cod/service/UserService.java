package com.cod.service;

import com.cod.dto.user.profile.ProfileOutput;
import com.cod.dto.user.profile.ProfileUpdate;
import com.cod.dto.user.search.UserSearchInput;
import com.cod.dto.user.search.UserSearchOutput;
import com.cod.dto.user.signin.SignInInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.dto.user.signin.SignInOutput;
import com.cod.dto.user.signup.SignUpInput;
import com.cod.dto.user.signup.SignUpOutput;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    ResponseEntity<Response<SignInOutput>> signIn(SignInInput signInInput);
    ResponseEntity<Response<SignUpOutput>> signUp(SignUpInput signUpInput);
    ResponseEntity<Response<Object>> deactivate();
    ResponseEntity<Response<ProfileOutput>> getProfile();
    ResponseEntity<Response<Object>> updateProfile(@RequestBody ProfileUpdate profileUpdate);
    ResponseEntity<PageResponse<UserSearchOutput>> findUserByNickname(UserSearchInput userSearchInput);
}
