package com.cod.dto.codidiary.createcodidiary;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateCodiDiaryInput {
    private LocalDate date;
    private String thumbnail;
    private String content;

}
