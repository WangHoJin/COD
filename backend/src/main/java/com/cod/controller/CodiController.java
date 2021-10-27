package com.cod.controller;

import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.codi.updatecodi.UpdateCodiInput;
import com.cod.response.PageResponse;
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

    /**
     * 코디 상세 조회 API
     * [GET] /api/codies/{id}
     * @return ResponseEntity<Response<SelectCommentOutput>>
     */
    // Params
    @GetMapping("/{id}")
    public ResponseEntity<Response<SelectCodiOutput>> getCodi(@PathVariable int id) {
        log.info("[GET] /api/codies/{id}");
        return codiService.selectCodi(id);
    }

    /**
     * 코디 리스트 조회 API
     * [GET] /api/codies?codiId=&...&page=&size=
     * @return ResponseEntity<PageResponse<SelectCommentOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectCodiOutput>> getCodiList(SelectCodiInput selectCommentInput) {
        log.info("[GET] /api/codies?codiId=&...&page=&size=");
        return codiService.selectCodiList(selectCommentInput);
    }

    /**
     * 코디 수정 API
     * [PATCH] /api/codies/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateCodi(@PathVariable("id") int id, @RequestBody UpdateCodiInput updateCodiInput) {
        log.info("[PATCH] /api/codies/" + id);
        return codiService.updateCodi(updateCodiInput, id);
    }

    /**
     * 코디 삭제 API
     * [DELETE] /api/codies/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteCodi(@PathVariable("id") int id) {
        log.info("[DELETE] /api/codies/" + id);
        return codiService.deleteCodi(id);
    }

}

