package com.cod.dto.wood.createwood;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateWoodInput {
    private String title;
    private String content;
    private LocalDate terminated_at;
}
