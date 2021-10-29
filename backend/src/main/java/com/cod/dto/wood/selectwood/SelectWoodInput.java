package com.cod.dto.wood.selectwood;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectWoodInput {
    private Integer userId;
    private int page;
    private int size;
}
