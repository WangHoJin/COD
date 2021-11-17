package com.cod.dto.cloth.selectcloth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectClothInput {
    private Integer userId;
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
    private int page;
    private int size;
}
