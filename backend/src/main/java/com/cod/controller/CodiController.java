package com.cod.controller;

import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.response.Response;
import com.cod.service.CodiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 댓글 CRUD API
 * 기웃기옷(피드)에 보이는 코디에 댓글을 작성할 수 있다.
 */
@RestController
@RequestMapping("/codies")
@RequiredArgsConstructor
@Slf4j
public class CodiController {

    private final CodiService codiService;

    /**
     * 코디 등록 API [POST] /api/codies
     *
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createCodi(@RequestBody CreateCodiInput createCodiInput) {
        log.info("[POST] /api/codies");
        return codiService.createCodi(createCodiInput);
    }

//    /**
//     * 댓글 조회 API
//     * [GET] /api/codies?codiId={codiId}
//     * @return ResponseEntity<PageResponse<SelectCommentOutput>>
//     */
//    // Params
//    @GetMapping
//    public ResponseEntity<PageResponse<SelectCommentOutput>> getCommentList(SelectCommentInput selectCommentInput) {
//        log.info("[GET] /api/codies?codiId=&page=&size=");
//        return commentService.selectComment(selectCommentInput);
//    }
//
//    /**
//     * 댓글 수정 API
//     * [PATCH] /api/codies/{id}
//     * @return ResponseEntity<Response<Object>>
//     */
//    // Body
//    @PatchMapping("/{id}")
//    public ResponseEntity<Response<Object>> updateComment(@PathVariable("id") int id, @RequestBody UpdateCommentInput updateCommentInput) {
//        log.info("[PATCH] /api/codies/" + id);
//        return commentService.updateComment(updateCommentInput, id);
//    }
//
//    /**
//     * 댓글 삭제 API
//     * [DELETE] /api/comments/{id}
//     * @return ResponseEntity<Response<Object>>
//     */
//    // Path-Variable
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Response<Object>> deleteComment(@PathVariable("id") int id) {
//        log.info("[DELETE] /api/codies/" + id);
//        return commentService.deleteComment(id);
//    }

}

