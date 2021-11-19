package com.cod.controller;

import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.dto.codi.getfollowingusercodi.GetFollowingUserCodiInput;
import com.cod.dto.codi.getpopularcodi.GetPopularCodiInput;
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
     * @return ResponseEntity<Response<SelectCodiOutput>>
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
     * @return ResponseEntity<PageResponse<SelectCodiOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectCodiOutput>> getCodiList(SelectCodiInput selectCodiInput) {
        log.info("[GET] /api/codies?codiId=&...&page=&size=");
        return codiService.selectCodiList(selectCodiInput);
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


    /**
     * 인기있는 코디 리스트 조회 API
     * startDate와 endDate 사이의 코디를 좋아요가 많은 순서대로 반환한다.
     * [GET] /api/codies?startDate=&endDate=&page=$size=
     * @return ResponseEntity<PageResponse<SelectCodiOutput>>
     */
    // Params
    @GetMapping("/popular")
    public ResponseEntity<PageResponse<SelectCodiOutput>> getPopularCodi(GetPopularCodiInput getPopularCodiInput) {
        log.info("[GET] /api/codies?startDate=&endDate=&page=$size=");
        return codiService.getPopularCodi(getPopularCodiInput);
    }

    /**
     * 팔로우한 유저들의 코디 리스트 조회 API
     * [GET] /api/codies?page=&size=
     * @return ResponseEntity<PageResponse<SelectCodiOutput>>
     */
    // Params
    @GetMapping("/following")
    public ResponseEntity<PageResponse<SelectCodiOutput>> getFollowingUserCodi(GetFollowingUserCodiInput getFollowingUserCodiInput) {
        log.info("[GET] /api/codies?page=&size=");
        return codiService.getFollowingUserCodi(getFollowingUserCodiInput);
    }

}

