package com.cod.dto.follow.deletefollow;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class DeleteFollowInput {
    private int toUserId;
}
