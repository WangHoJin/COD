package com.cod.dto.comment.createcomment;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateCommentInput {
    private int codiId; //댓글을 단 코디 id
    private String content; //댓글 내용
}
