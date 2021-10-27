package com.cod.dto.user.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfileUpdate {
    private String profile;
    private String introduction;
    private String nickname;
}
