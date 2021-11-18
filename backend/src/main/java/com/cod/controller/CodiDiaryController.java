package com.cod.controller;

import com.cod.dto.codi.createcodi.CreateDiaryInput;
import com.cod.dto.codi.selectcodi.SelectDiaryInput;
import com.cod.dto.codi.selectcodi.SelectDiaryOutput;
import com.cod.dto.codi.updatecodi.UpdateDiaryInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.CodiDiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {

    private final CodiDiaryService codiService;

    /**
     * 코디일기 등록 API [POST] /api/diaries
     *
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createDiary(@RequestBody CreateDiaryInput createDiaryInput) {
        log.info("[POST] /api/diaries");
        return codiService.createDiary(createDiaryInput);
    }

    /**
     * 코디일기 상세 조회 API
     * [GET] /api/diaries/{id}
     * @return ResponseEntity<Response<SelectDiaryOutput>>
     */
    // Params
    @GetMapping("/{id}")
    public ResponseEntity<Response<SelectDiaryOutput>> getDiary(@PathVariable int id) {
        log.info("[GET] /api/diaries/{id}");
        return codiService.selectDiary(id);
    }

    /**
     * 코디일기 리스트 조회 API
     * [GET] /api/diaries?month=&size=
     * @return ResponseEntity<PageResponse<SelectDiaryOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectDiaryOutput>> getDiaryList(SelectDiaryInput selectDiaryInput) {
        log.info("[GET] /api/diaries?month=&...&page=&size=");
        return codiService.selectDiaryList(selectDiaryInput);
    }

    /**
     * 코디일기 수정 API
     * [PATCH] /api/diaries/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateDiary(@PathVariable("id") int id, @RequestBody UpdateDiaryInput updateDiaryInput) {
        log.info("[PATCH] /api/diaries/" + id);
        return codiService.updateDiary(updateDiaryInput, id);
    }

    /**
     * 코디일기 삭제 API
     * [DELETE] /api/diaries/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteDiary(@PathVariable("id") int id) {
        log.info("[DELETE] /api/diaries/" + id);
        return codiService.deleteDiary(id);
    }
}

