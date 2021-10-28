package com.cod.dto.woodcodi.selectwoodcodi;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectWoodCodiOutput {
    private int woodId;
    private int userId;
    private String codiName;
    private String codiTag;
    private String codiDescription;
    private String codiThumbnail;
    private String codiCoordinate;
    private LocalDateTime codiCreatedAt;
    private LocalDateTime codiUpdatedAt;
    private int liked;

    @QueryProjection
    public SelectWoodCodiOutput(int woodId, int userId, String codiName, String codiTag, String codiDescription, String codiThumbnail, String codiCoordinate, LocalDateTime codiCreatedAt, LocalDateTime codiUpdatedAt, int liked) {
        this.woodId = woodId;
        this.userId = userId;
        this.codiName = codiName;
        this.codiTag = codiTag;
        this.codiDescription = codiDescription;
        this.codiThumbnail = codiThumbnail;
        this.codiCoordinate = codiCoordinate;
        this.codiCreatedAt = codiCreatedAt;
        this.codiUpdatedAt = codiUpdatedAt;
        this.liked = liked;
    }
}
