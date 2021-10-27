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
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    Response<SignInOutput> signIn(SignInInput signInInput);
    Response<SignUpOutput> signUp(SignUpInput signUpInput);
    Response<Object> deactivate();
    Response<ProfileOutput> getProfile();
    Response<Object> updateProfile(@RequestBody ProfileUpdate profileUpdate);
    PageResponse<UserSearchOutput> getUser(UserSearchInput userSearchInput);
}
