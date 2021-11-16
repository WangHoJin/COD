package com.cod.dto.user.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfileOutput {
    private int userId;
    private String email;
    private String name;
    private String nickname;
    private String gender;
    private LocalDate birth;
    private String profile;
    private String introduction;

}
