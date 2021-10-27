package com.cod.dto.codi.getpopularcodi;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetPopularCodiInput {
    private String startDate;
    private String endDate;
    private int page;
    private int size;
}
