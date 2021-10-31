package com.cod.controller;

import com.cod.dto.grade.selectgrade.SelectGradeOutput;
import com.cod.dto.grade.updategrade.UpdateGradeInput;
import com.cod.response.Response;
import com.cod.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 업적 CRUD API
 * 마이페이지에서 업적를 확인할 수 있다.
 */
@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
@Slf4j
public class GradeController {

    private final GradeService gradeService;

    /**
     * 업적 조회 API
     * [GET] /api/grades?userId=
     *
     * @return ResponseEntity<PageResponse < SelectWoodListOutput>>
     */
    @GetMapping
    public ResponseEntity<Response<SelectGradeOutput>> selectGrade(int userId) {
        log.info("[GET] /api/grades?userId=");
        return gradeService.selectGrade(userId);
    }

    /**
     * 업적 수정 API
     * [PATCH] /api/grades?userId=
     *
     * @return ResponseEntity<PageResponse < SelectWoodListOutput>>
     */
    @PatchMapping
    public ResponseEntity<Response<Object>> updateGrade(int userId, @RequestBody UpdateGradeInput updateGradeInput) {
        log.info("[PATCH] /api/grades?userId=");
        return gradeService.updateGrade(userId, updateGradeInput);
    }
}

