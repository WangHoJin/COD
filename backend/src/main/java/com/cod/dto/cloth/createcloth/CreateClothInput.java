package com.cod.dto.cloth.createcloth;
import com.cod.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateClothInput {
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
