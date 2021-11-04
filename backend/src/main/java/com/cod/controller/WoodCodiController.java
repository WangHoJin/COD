package com.cod.controller;

import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.codi.updatecodi.UpdateCodiInput;
import com.cod.dto.woodcodi.createwoodcodi.CreateWoodCodiInput;
import com.cod.dto.woodcodi.selectwoodcodi.SelectWoodCodiInput;
import com.cod.dto.woodcodi.selectwoodcodi.SelectWoodCodiOutput;
import com.cod.dto.woodcodi.updatewoodcodi.UpdateWoodCodiInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.WoodCodiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 코디나무숲 (구 챌린지)에 사용되는 코디들 CRUD
 */
@RestController
@RequestMapping("/wood-codies")
@RequiredArgsConstructor
@Slf4j
public class WoodCodiController {

    private final WoodCodiService woodCodiService;

    /**
     * 코디나무 코디 등록 API [POST] /api/wood-codies
     *
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createWoodCodi(@RequestBody CreateWoodCodiInput createWoodCodiInput) {
        log.info("[POST] /api/wood-codies");
        return woodCodiService.createWoodCodi(createWoodCodiInput);
    }

    /**
     * 코디나무 코디 상세 조회 API
     * [GET] /api/wood-codies/{id}
     * @return ResponseEntity<Response<SelectWoodCodiOutput>>
     */
    // Params
    @GetMapping("/{id}")
    public ResponseEntity<Response<SelectWoodCodiOutput>> getWoodCodi(@PathVariable int id) {
        log.info("[GET] /api/wood-codies/{id}");
        return woodCodiService.selectWoodCodi(id);
    }

    /**
     * 코디나무 코디 리스트 조회 API
     * [GET] /api/wood-codies?woodId=&...&page=&size=
     * @return ResponseEntity<PageResponse<SelectWoodCodiOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectWoodCodiOutput>> getWoodCodiList(SelectWoodCodiInput selectWoodCodiInput) {
        log.info("[GET] /api/wood-codies?codiId=&...&page=&size=");
        return woodCodiService.selectWoodCodiList(selectWoodCodiInput);
    }

    /**
     * 코디나무 코디 수정 API
     * [PATCH] /api/wood-codies/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateWoodCodi(@PathVariable("id") int id, @RequestBody UpdateWoodCodiInput updateWoodCodiInput) {
        log.info("[PATCH] /api/wood-codies/" + id);
        return woodCodiService.updateWoodCodi(updateWoodCodiInput, id);
    }

    /**
     * 코디나무 코디 삭제 API
     * [DELETE] /api/wood-codies/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteWoodCodi(@PathVariable("id") int id) {
        log.info("[DELETE] /api/wood-codies/" + id);
        return woodCodiService.deleteWoodCodi(id);
    }

}

