package com.cod.service;

import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface CodiService {
    ResponseEntity<Response<Object>> createCodi(CreateCodiInput createCodiInput);
//    ResponseEntity<PageResponse<SelectCommentOutput>> selectComment(SelectCommentInput selectCommentInput);
//    ResponseEntity<Response<Object>> updateComment(UpdateCommentInput updateReviewInput, int commentId);
//    ResponseEntity<Response<Object>> deleteComment(int commentId);
}
