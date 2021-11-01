package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.WoodCodiRepository;
import com.cod.dao.WoodRepository;
import com.cod.dto.wood.createwood.CreateWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodListOutput;
import com.cod.dto.wood.selectwood.SelectWoodOutput;
import com.cod.dto.wood.updatewood.UpdateWoodInput;
import com.cod.entity.User;
import com.cod.entity.Wood;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.response.ResponseStatus;
import com.cod.service.JwtService;
import com.cod.service.WoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cod.response.ResponseStatus.*;

@Service("WoodService")
@RequiredArgsConstructor
@Slf4j
public class WoodServiceImpl implements WoodService {

    private final WoodRepository woodRepository;
    private final WoodCodiRepository woodCodiRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createWood(CreateWoodInput createWoodInput) {
        // 1. 값 형식 체크
        if (createWoodInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(ResponseStatus.NO_VALUES));
        if (!ValidationCheck.isValid(createWoodInput.getTitle())
                || !ValidationCheck.isValidLocalDate(createWoodInput.getTerminated_at())
                || !ValidationCheck.isValid(createWoodInput.getContent()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(ResponseStatus.NO_VALUES));

        // 2. 코디나무 생성
        try {
            User loginUser = jwtService.getUser();
            if (loginUser == null) {
                log.error("[GET]/woods NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }

            Wood wood = Wood.builder()
                    .user(loginUser)
                    .title(createWoodInput.getTitle())
                    .content(createWoodInput.getContent())
                    .terminatedAt(createWoodInput.getTerminated_at())
                    .build();

            woodRepository.save(wood);

        } catch (Exception e) {
            log.error("[woods/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null, CREATED_WOOD));
    }

    @Override
    public ResponseEntity<Response<SelectWoodOutput>> selectWood(int woodId) {
        // 1. 값 형식 체크
        if (!ValidationCheck.isValidId(woodId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));

        // 2. 코디나무 상세 조회
        Wood wood;
        SelectWoodOutput selectWoodOutput;

        try {
            wood = woodRepository.findById(woodId).orElse(null);
            if (wood == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_WOOD));
        } catch (Exception e) {
            log.error("[woods/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }
        // 3. 출력값 정리
        selectWoodOutput = SelectWoodOutput.builder()
                .userId(wood.getUser().getId())
                .woodTitle(wood.getTitle())
                .woodContent(wood.getContent())
                .WoodTerminatedAt(wood.getTerminatedAt())
                .woodCodiCnt(woodCodiRepository.countByWood(wood))
                .build();

        // 4. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(selectWoodOutput, SUCCESS_SELECT_WOOD));
    }

    @Override
    public ResponseEntity<PageResponse<SelectWoodListOutput>> selectWoodList(SelectWoodInput selectWoodInput) {
        // 1. 값 형식 체크
        if (selectWoodInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidPage(selectWoodInput.getPage())
                || !ValidationCheck.isValidId(selectWoodInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));

        // 2. 코디나무 조회
        Pageable pageable = PageRequest.of(selectWoodInput.getPage() - 1, selectWoodInput.getSize());
        Page<SelectWoodListOutput> woodList;

        try {
            woodList = woodRepository.findByDynamicQuery(selectWoodInput, pageable);
        } catch (Exception e) {
            log.error("[woods/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(woodList, SUCCESS_SELECT_WOOD_CODI));
    }


    @Override
    @Transactional
    public ResponseEntity<Response<Object>> updateWood(int woodId, UpdateWoodInput updateWoodInput) {
        try {
            // 1. 코디나무 조회
            Wood wood = woodRepository.findById(woodId).orElse(null);

            // 2. 코디나무 수정
            if (wood == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_WOOD));
            }

            if (StringUtils.isNoneBlank(updateWoodInput.getTitle()))
                wood.setTitle(updateWoodInput.getTitle());
            if (StringUtils.isNoneBlank(updateWoodInput.getContent()))
                wood.setContent(updateWoodInput.getContent());
            if (StringUtils.isNoneBlank(updateWoodInput.getTerminated_at().toString())) {
                wood.setTerminatedAt(updateWoodInput.getTerminated_at());
            }

            woodRepository.save(wood);
        } catch (Exception e) {
            log.error("[woods/patch] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_UPDATE_WOOD));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteWood(int woodId) {
        try {
            // 1. 코디나무 조회
            Wood wood = woodRepository.findById(woodId).orElse(null);

            // 2. 코디나무 삭제
            if (wood == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));

            woodRepository.delete(wood);

        } catch (Exception e) {
            log.error("[woods/delete] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_DELETE_CODI));
    }
}
