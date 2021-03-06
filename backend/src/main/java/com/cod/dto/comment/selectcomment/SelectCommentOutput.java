package com.cod.dto.comment.selectcomment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SelectCommentOutput {
    private Integer commentId;
    private Integer codiId;
    private Integer userId;
    private String userProfileImg;
    private String userNickname;
    private String commentContent;
    private LocalDateTime commentCreatedAt;
    private LocalDateTime commentUpdatedAt;
}
