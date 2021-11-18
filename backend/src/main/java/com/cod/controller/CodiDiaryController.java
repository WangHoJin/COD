package com.cod.controller;

import com.cod.dto.codidiary.createcodidiary.CreateCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryOutput;
import com.cod.dto.codidiary.updatecodidiary.UpdateCodiDiaryInput;
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
public class CodiDiaryController {

    private final CodiDiaryService codiService;

    /**
     * 코디일기 등록 API [POST] /api/diaries
     *
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createCodiDiary(@RequestBody CreateCodiDiaryInput createCodiDiaryInput) {
        log.info("[POST] /api/diaries");
        return codiService.createCodiDiary(createCodiDiaryInput);
    }

    /**
     * 코디일기 상세 조회 API
     * [GET] /api/diaries/{id}
     * @return ResponseEntity<Response<SelectCodiDiaryOutput>>
     */
    // Params
    @GetMapping("/{id}")
    public ResponseEntity<Response<SelectCodiDiaryOutput>> getCodiDiary(@PathVariable int id) {
        log.info("[GET] /api/diaries/{id}");
        return codiService.selectCodiDiary(id);
    }

    /**
     * 코디일기 리스트 조회 API
     * [GET] /api/diaries?month=&size=
     * @return ResponseEntity<PageResponse<SelectCodiDiaryOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectCodiDiaryOutput>> getCodiDiaryList(SelectCodiDiaryInput selectCodiDiaryInput) {
        log.info("[GET] /api/diaries?month=&...&page=&size=");
        return codiService.selectCodiDiaryList(selectCodiDiaryInput);
    }

    /**
     * 코디일기 수정 API
     * [PATCH] /api/diaries/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateCodiDiary(@PathVariable("id") int id, @RequestBody UpdateCodiDiaryInput updateCodiDiaryInput) {
        log.info("[PATCH] /api/diaries/" + id);
        return codiService.updateCodiDiary(updateCodiDiaryInput, id);
    }

    /**
     * 코디일기 삭제 API
     * [DELETE] /api/diaries/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteCodiDiary(@PathVariable("id") int id) {
        log.info("[DELETE] /api/diaries/" + id);
        return codiService.deleteCodiDiary(id);
    }
}

