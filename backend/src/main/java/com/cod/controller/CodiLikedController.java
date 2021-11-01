package com.cod.controller;

import com.cod.dto.codiliked.createcodiliked.CreateCodiLikedInput;
import com.cod.dto.codiliked.deletecodiliked.DeleteCodiLiked;
import com.cod.dto.codiliked.selectcodilikedlist.SelectCodiLikedListInput;
import com.cod.dto.codiliked.selectcodilikedlist.SelectCodiLikedListOutput;
import com.cod.response.PageResponse;
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
    public ResponseEntity<Response<Object>> createCodiLiked(@RequestBody CreateCodiLikedInput createCodiLikedInput){
        log.info("[POST] /codi-liked");
        return codiLikedService.createCodiLiked(createCodiLikedInput);
    }

    /**
     * 코디 좋아요 취소 API
     * [DELETE] /codi-liked?codiId={codiId}
     *
     * @return ResponseEntity<Response<Object>>
     */
    @DeleteMapping
    public ResponseEntity<Response<Object>> deleteCodiLiked(DeleteCodiLiked deleteCodiLiked){
        log.info("[DELETE] /codi-liked?codiId={codiId}");
        return codiLikedService.deleteCodiLiked(deleteCodiLiked);
    }

    /**
     * 좋아요 누른 코디 조회 API
     * [GET] /codi-liked?page=&size
     *
     * @return ResponseEntity<PageResponse<SelectCodiLiked>>
     */
    @GetMapping
    public ResponseEntity<PageResponse<SelectCodiLikedListOutput>> selectCodiLikedList(SelectCodiLikedListInput selectCodiLikedListInput){
        log.info("[GET] /codi-liked?page=&size=");
        return codiLikedService.selectCodiLikedList(selectCodiLikedListInput);
    }

}
