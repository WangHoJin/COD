package com.cod.dto.codidiary.selectcodidiary;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectCodiDiaryOutput {
    private int codiDiaryId;
    private int userId;
    private DateTime codiDiaryDate;
    private String codiDiaryThumbnail;
    private String codiDiaryContent;
    private LocalDateTime codiDiaryCreatedAt;
    private LocalDateTime codiDiaryUpdatedAt;

    @QueryProjection
    public SelectCodiDiaryOutput(int codiDiaryId, int userId, DateTime codiDiaryDate, String codiDiaryThumbnail, String codiDiaryContent, LocalDateTime codiDiaryCreatedAt, LocalDateTime codiDiaryUpdatedAt) {
        this.codiDiaryId = codiDiaryId;
        this.userId = userId;
        this.codiDiaryDate = codiDiaryDate;
        this.codiDiaryThumbnail = codiDiaryThumbnail;
        this.codiDiaryContent= codiDiaryContent;
        this.codiDiaryCreatedAt = codiDiaryCreatedAt;
        this.codiDiaryUpdatedAt = codiDiaryUpdatedAt;
    }
}
