package com.cod.controller;

import com.cod.dto.codiliked.createlike.CreateLikeInput;
import com.cod.response.Response;
import com.cod.service.CodiLikedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/codi-liked")
@RequiredArgsConstructor
@Slf4j
public class CodiLikedController {
    private final CodiLikedService codiLikedService;

    /**
     * 코디 좋아요 등록 API
     * [POST] /codi-liked
     *
     * @return ResponseEntity<Response<Object>>
     */
    @PostMapping
    public ResponseEntity<Response<Object>> createLike(@RequestBody CreateLikeInput createLikeInput){
        log.info("[POST] /codi-liked");
        return codiLikedService.createLike(createLikeInput);
    }

    /**
     * 코디 좋아요 취소 API
     * [DELETE] /codi-liked?codiId={codiId}
     *
     * @return ResponseEntity<Response<Object>>
     */
    @DeleteMapping
    public ResponseEntity<Response<Object>> deleteLike(@PathVariable("id") int id){
        log.info("[DELETE] /codi-liked");
        return codiLikedService.deleteLike(id);
    }
}
