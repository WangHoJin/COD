package com.cod.controller;

import com.cod.dto.woodcodiliked.createwoodcodiliked.CreateWoodCodiLikedInput;
import com.cod.dto.woodcodiliked.deletewoodcodiliked.DeleteWoodCodiLikedInput;
import com.cod.response.Response;
import com.cod.service.WoodCodiLikedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wood-codi-liked")
@RequiredArgsConstructor
@Slf4j
public class WoodCodiLikedController {

    private final WoodCodiLikedService woodCodiLikedService;

    /**
     * 코디나무 코디 좋아요 등록 API
     * [POST] /wood-codi-liked
     *
     * @return ResponseEntity<Response<Object>>
     */
    @PostMapping
    public ResponseEntity<Response<Object>> createWoodCodiLiked(@RequestBody CreateWoodCodiLikedInput createWoodCodiLikedInput){
        log.info("[POST] /wood-codi-liked");
        return woodCodiLikedService.createWoodCodiLiked(createWoodCodiLikedInput);
    }

    /**
     * 코디나무 코디 좋아요 취소 API
     * [DELETE] /wood-codi-liked?woodCodiId={woodCodiId}
     *
     * @return ResponseEntity<Response<Object>>
     */
    @DeleteMapping
    public ResponseEntity<Response<Object>> deleteWoodCodiLiked(DeleteWoodCodiLikedInput deleteWoodCodiLikedInput){
        log.info("[DELETE] /wood-codi-liked?woodCodiId=");
        return woodCodiLikedService.deleteWoodCodiLiked(deleteWoodCodiLikedInput);
    }
}
