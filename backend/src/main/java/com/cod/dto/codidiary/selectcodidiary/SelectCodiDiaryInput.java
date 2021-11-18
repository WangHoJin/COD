package com.cod.dto.codidiary.selectcodidiary;

import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectCodiDiaryInput {
    private Integer userId;
    private DateTime date;
    private int page;
    private int size;
}
