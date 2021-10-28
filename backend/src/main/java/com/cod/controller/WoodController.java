package com.cod.controller;

import com.cod.dto.wood.createwood.CreateWoodInput;
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
        System.out.println(createWoodInput.getTerminated_at());
        return woodService.createWood(createWoodInput);
    }
}

