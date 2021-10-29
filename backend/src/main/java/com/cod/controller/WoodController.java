package com.cod.controller;

import com.cod.dto.wood.createwood.CreateWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodOutput;
import com.cod.dto.wood.updatewood.UpdateWoodInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.WoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 코디나무 CRUD API
 * 메인페이지, 마이페이지에서 코디나무를 확인할 수 있다.
 */
@RestController
@RequestMapping("/woods")
@RequiredArgsConstructor
@Slf4j
public class WoodController {

    private final WoodService woodService;

    /**
     * 코디나무 등록 API
     * [POST] /api/woods
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createWood(@RequestBody CreateWoodInput createWoodInput) {
        log.info("[POST] /api/woods");
        return woodService.createWood(createWoodInput);
    }

    /**
     * 코디나무 리스트 조회 API
     * [GET] /api/woods?userId=&page=&size=
     * @return ResponseEntity<PageResponse<SelectWoodOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectWoodOutput>> selectWoodList(SelectWoodInput selectWoodInput) {
        log.info("[GET] /api/woods?userId=&page=&size=");
        return woodService.selectWoodList(selectWoodInput);
    }

    /**
     * 코디나무 수정 API
     * [PATCH] /api/woods/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateWood(@PathVariable("id") int id, @RequestBody UpdateWoodInput updateWoodInput) {
        log.info("[PATCH] /api/woods/{id}");
        return woodService.updateWood(id, updateWoodInput);
    }

    /**
     * 코디나무 삭제 API
     * [DELETE] /api/woods/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // PathVariable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteWood(@PathVariable("id") int id) {
        log.info("[DELETE] /api/woods/{id}");
        return woodService.deleteWood(id);
    }
}

