package com.cod.dto.woodcodi.selectwoodcodi;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectWoodCodiInput {
    private int woodId;
    private int page;
    private int size;
}
