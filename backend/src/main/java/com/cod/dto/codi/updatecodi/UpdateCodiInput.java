package com.cod.dto.codi.updatecodi;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateCodiInput {
    private String name;
    private String tag;
    private String description;
    private String thumbnail;
    private String coordinate;
}
