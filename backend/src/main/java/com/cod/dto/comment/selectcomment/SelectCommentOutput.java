package com.cod.dto.comment.selectcomment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class SelectCommentOutput {
    private Integer codiId;
    private Integer userId;
    private String commentContent;
    private LocalDateTime commentCreatedAt;
    private LocalDateTime commentUpdatedAt;

    @QueryProjection
    public SelectCommentOutput(Integer codiId, Integer userId, String commentContent, LocalDateTime commentCreatedAt, LocalDateTime commentUpdatedAt) {
        this.codiId = codiId;
        this.userId = userId;
        this.commentContent = commentContent;
        this.commentCreatedAt = commentCreatedAt;
        this.commentUpdatedAt = commentUpdatedAt;
    }
}
