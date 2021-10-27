package com.cod.dto.comment.updatecomment;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateCommentInput {
    private String content;
}
