package com.cod.dto.codiliked.createlike;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateLikeInput {
    private int codiId;
    private int userId;
}
