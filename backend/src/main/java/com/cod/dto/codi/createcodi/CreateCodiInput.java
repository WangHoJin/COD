package com.cod.dto.codi.createcodi;

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
public class CreateCodiInput {
    private int userId;
    private String name;
    private String tag;
    private String description;
    private String thumbnail;
    private String coordinate;
}
