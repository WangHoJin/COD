package com.cod.dto.woodcodi.updatewoodcodi;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateWoodCodiInput {
    private String name;
    private String tag;
    private String description;
    private String thumbnail;
    private String coordinate;
}
