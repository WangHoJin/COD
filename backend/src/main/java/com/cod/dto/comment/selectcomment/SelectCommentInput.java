package com.cod.dto.comment.selectcomment;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectCommentInput {
    private Integer codiId;
    private int page;
    private int size;
}
