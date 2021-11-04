package com.cod.dto.user.signup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter @Setter
public class SignUpInput {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private LocalDate birth;
    private String gender;
    private String profile;
    private String introduction;
    private String oauth;
}
