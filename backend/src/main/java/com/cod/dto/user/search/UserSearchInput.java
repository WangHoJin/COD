package com.cod.dto.user.search;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class UserSearchInput {
    private String nickname;
    private int page;
    private int size;
}
