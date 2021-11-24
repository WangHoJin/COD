package com.cod.dto.codi.getfollowingusercodi;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetFollowingUserCodiInput {
    private int page;
    private int size;
}
