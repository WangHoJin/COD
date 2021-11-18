package com.cod.dto.codidiary.selectcodidiary;

import lombok.*;
import org.joda.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectCodiDiaryInput {
    private Integer userId;
    private LocalDate date;
    private int page;
    private int size;
}
