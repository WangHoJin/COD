package com.cod.dto.wood.selectwood;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectWoodListOutput {
    private int userId;
    private String woodTitle;
    private String woodContent;
    private LocalDate woodTerminatedAt;

    @QueryProjection
    public SelectWoodListOutput(int userId, String woodTitle, String woodContent, LocalDate woodTerminatedAt) {
        this.userId = userId;
        this.woodTitle = woodTitle;
        this.woodContent = woodContent;
        this.woodTerminatedAt = woodTerminatedAt;
    }
}
