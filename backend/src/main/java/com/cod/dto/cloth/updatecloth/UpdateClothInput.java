package com.cod.dto.cloth.updatecloth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateClothInput {
    private String imgUrl;
    private String name;
    private String type;
    private String color;
    private String season;
    private Boolean isOwned;
    private String tag;
    private Integer price;
    private String brand;
    private String measure;
}
