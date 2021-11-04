package com.cod.dto.codiliked.selectcodilikedlist;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectCodiLikedListInput {
    private int page;
    private int size;
}
