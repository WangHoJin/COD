package com.cod.dto.follow.selectfollow;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class SelectFollowInput {
    private Integer fromUserId;
    private Integer toUserId;
    private int page;
    private int size;
}
