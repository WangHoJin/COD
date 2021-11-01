package com.cod.dto.wood.selectwood;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectWoodOutput {
    private int userId;
    private String woodTitle;
    private String woodContent;
    private LocalDate WoodTerminatedAt;
    private int woodCodiCnt;

    @QueryProjection
    public SelectWoodOutput(int userId, String woodTitle, String woodContent, LocalDate WoodTerminatedAt, int woodCodiCnt) {
        this.userId = userId;
        this.woodTitle = woodTitle;
        this.woodContent = woodContent;
        this.WoodTerminatedAt = WoodTerminatedAt;
        this.woodCodiCnt = woodCodiCnt;
    }
}
