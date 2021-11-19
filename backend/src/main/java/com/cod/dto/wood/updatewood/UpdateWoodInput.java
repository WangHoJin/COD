package com.cod.dto.wood.updatewood;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateWoodInput {
    private String title;
    private String content;
    private LocalDate terminated_at;
}
