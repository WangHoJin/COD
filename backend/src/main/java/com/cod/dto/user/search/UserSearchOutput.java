package com.cod.dto.user.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSearchOutput {
    private int userId;
    private String profile;
    private String email;
    private String nickname;
    private String introduction;
}
