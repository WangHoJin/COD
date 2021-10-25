package com.cod.service;

import com.cod.dto.user.signin.SignInInput;
import com.cod.response.Response;
import com.cod.dto.user.signin.SignInOutput;
import com.cod.dto.user.signup.SignUpInput;
import com.cod.dto.user.signup.SignUpOutput;

public interface UserService {
    Response<SignInOutput> signIn(SignInInput signInInput);
    Response<SignUpOutput> signUp(SignUpInput signUpInput);
}
