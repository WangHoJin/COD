package com.cod.service;

import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<Response<Object>> createComment(CreateCommentInput createCommentInput);
    ResponseEntity<PageResponse<SelectCommentOutput>> selectCommentList(SelectCommentInput selectCommentInput);
    ResponseEntity<Response<Object>> updateComment(UpdateCommentInput updateCommentInput, int commentId);
    ResponseEntity<Response<Object>> deleteComment(int commentId);
}
