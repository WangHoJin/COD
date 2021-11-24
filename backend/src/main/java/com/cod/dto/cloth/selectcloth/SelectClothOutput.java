package com.cod.dto.cloth.selectcloth;

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
public class SelectClothOutput {
    private int clothId;
    private int userId;
    private String clothName;
    private String clothTag;
    private String clothImgUrl;
    private String clothType;
    private String clothColor;
    private String clothSeason;
    private Boolean clothIsOwned;
    private String clothBrand;
    private Integer clothPrice;
    private String clothMeasure;
    private LocalDateTime clothCreatedAt;
    private LocalDateTime clothUpdatedAt;

    @QueryProjection
    public SelectClothOutput(int clothId, int userId, String clothName, String clothTag, String clothImgUrl, String clothType, String clothColor, String clothSeason, Boolean clothIsOwned, String clothBrand, Integer clothPrice, String clothMeasure, LocalDateTime clothCreatedAt, LocalDateTime clothUpdatedAt) {
        this.clothId = clothId;
        this.userId = userId;
        this.clothName = clothName;
        this.clothTag = clothTag;
        this.clothImgUrl = clothImgUrl;
        this.clothType = clothType;
        this.clothColor = clothColor;
        this.clothSeason = clothSeason;
        this.clothIsOwned = clothIsOwned;
        this.clothBrand = clothBrand;
        this.clothPrice = clothPrice;
        this.clothMeasure = clothMeasure;
        this.clothCreatedAt = clothCreatedAt;
        this.clothUpdatedAt = clothUpdatedAt;
    }
}
