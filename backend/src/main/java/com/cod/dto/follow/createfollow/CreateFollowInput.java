package com.cod.dto.follow.createfollow;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateFollowInput {
    private int toUserId;
}
