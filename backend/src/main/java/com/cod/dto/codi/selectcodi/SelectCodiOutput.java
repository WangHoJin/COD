package com.cod.dto.codi.selectcodi;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectCodiOutput {
    private int codiId;
    private int userId;
    private String userNickname;
    private String userProfileImg;
    private String codiName;
    private String codiTag;
    private String codiDescription;
    private String codiThumbnail;
    private String codiCoordinate;
    private LocalDateTime codiCreatedAt;
    private LocalDateTime codiUpdatedAt;
    private int liked;
    private int comment;

    @QueryProjection
    public SelectCodiOutput(int codiId, int userId, String userNickname, String userProfileImg, String codiName, String codiTag, String codiDescription, String codiThumbnail, String codiCoordinate, LocalDateTime codiCreatedAt, LocalDateTime codiUpdatedAt, int liked, int comment) {
        this.codiId = codiId;
        this.userId = userId;
        this.userNickname = userNickname;
        this.userProfileImg = userProfileImg;
        this.codiName = codiName;
        this.codiTag = codiTag;
        this.codiDescription = codiDescription;
        this.codiThumbnail = codiThumbnail;
        this.codiCoordinate = codiCoordinate;
        this.codiCreatedAt = codiCreatedAt;
        this.codiUpdatedAt = codiUpdatedAt;
        this.liked = liked;
        this.comment=comment;
    }
}
