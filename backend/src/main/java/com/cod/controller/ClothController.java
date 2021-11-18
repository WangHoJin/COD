package com.cod.controller;

import com.cod.dto.cloth.createcloth.CreateClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothOutput;
import com.cod.dto.cloth.updatecloth.UpdateClothInput;
import com.cod.service.ClothService;
import com.cod.response.PageResponse;
import com.cod.response.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cod.response.ResponseStatus.*;

@RestController
@RequestMapping("/clothes")
@RequiredArgsConstructor
@Slf4j
public class ClothController {

    private final ClothService clothService;

    /**
     * 옷 등록 API [POST] /api/clothes
     *
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createCloth(@RequestBody CreateClothInput createClothInput) {
        log.info("[POST] /api/clothes");
        return clothService.createCloth(createClothInput);
    }

    /**
     * 옷 상세 조회 API
     * [GET] /api/clothes/{id}
     * @return ResponseEntity<Response<SelectClothOutput>>
     */
    // Params
    @GetMapping("/{id}")
    public ResponseEntity<Response<SelectClothOutput>> getCloth(@PathVariable int id) {
        log.info("[GET] /api/clothes/{id}");
        return clothService.selectCloth(id);
    }

    /**
     * 옷 리스트 조회 API
     * [GET] /api/clothes?userId=&...&page=&size=
     * @return ResponseEntity<PageResponse<SelectClothOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectClothOutput>> getClothList(SelectClothInput selectClothInput) {
        if(selectClothInput.getUserId()==null && selectClothInput.getType()==null){
            log.info("[GET] /clothes?NO_VALID_STATUS");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PageResponse<>(NO_VALUES));
        }
        if(selectClothInput.getType()!=null){
            log.info("[GET] /api/clothes?userId=&page=&size=");
            return clothService.selectClothList(selectClothInput,false);
        }
        else{
            log.info("[GET] /api/clothes?userId=&type=&page=&size=");
            return clothService.selectClothList(selectClothInput,true);
        }



    }

    /**
     * 옷 수정 API
     * [PATCH] /api/clothes/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateCloth(@PathVariable("id") int id, @RequestBody UpdateClothInput updateClothInput) {
        log.info("[PATCH] /api/clothes/" + id);
        return clothService.updateCloth(updateClothInput, id);
    }

    /**
     * 옷 삭제 API
     * [DELETE] /api/clothes/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteCloth(@PathVariable("id") int id) {
        log.info("[DELETE] /api/clothes/" + id);
        return clothService.deleteCloth(id);
    }




}

