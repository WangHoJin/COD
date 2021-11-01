package com.cod.dto.wood.selectwood;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectWoodOutput {
    private int woodId;
    private int userId;
    private String woodTitle;
    private String woodContent;
    private LocalDate woodTerminatedAt;
    private int woodCodiCnt;

    @QueryProjection
    public SelectWoodOutput(int woodId, int userId, String woodTitle, String woodContent, LocalDate woodTerminatedAt, int woodCodiCnt) {
        this.woodId = woodId;
        this.userId = userId;
        this.woodTitle = woodTitle;
        this.woodContent = woodContent;
        this.woodTerminatedAt = woodTerminatedAt;
        this.woodCodiCnt = woodCodiCnt;
    }
}
