package com.cod.dto.woodcodi.createwoodcodi;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateWoodCodiInput {
    private int woodId; //코디나무 번호
    private String name;
    private String tag;
    private String description;
    private String thumbnail;
    private String coordinate;
}
