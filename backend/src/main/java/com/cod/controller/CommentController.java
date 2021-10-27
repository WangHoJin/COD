package com.cod.controller;

import com.cod.configuration.ValidationCheck;
import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 댓글 CRUD API
 * 기웃기옷(피드)에 보이는 코디에 댓글을 작성할 수 있다.
 */
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성 API [POST] /api/comment
     *
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createComment(@RequestBody CreateCommentInput createCommentInput) {
        log.info("[POST] /api/comments");
        return commentService.createComment(createCommentInput);
    }

    /**
     * 댓글 조회 API
     * [GET] /api/comments?codiId={codiId}
     * @return ResponseEntity<PageResponse<SelectCommentOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectCommentOutput>> getCommentList(SelectCommentInput selectCommentInput) {
        log.info("[GET] /api/comments?codiId=&page=&size=");
        return commentService.selectComment(selectCommentInput);
    }

    /**
     * 댓글 수정 API
     * [PATCH] /api/comments/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateComment(@PathVariable("id") int id, @RequestBody UpdateCommentInput updateCommentInput) {
        log.info("[PATCH] /api/comments/" + id);
        return commentService.updateComment(updateCommentInput, id);
    }

    /**
     * 댓글 삭제 API
     * [DELETE] /api/comments/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteComment(@PathVariable("id") int id) {
        log.info("[DELETE] /api/comments/" + id);
        return commentService.deleteComment(id);
    }

}

