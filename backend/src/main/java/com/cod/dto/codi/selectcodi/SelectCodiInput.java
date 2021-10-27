package com.cod.dto.codi.selectcodi;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectCodiInput {
    private Integer userId;
    private String name;
    private String tag;
    private String description;
}
