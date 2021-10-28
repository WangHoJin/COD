package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.WoodCodiLikedRepository;
import com.cod.dao.WoodCodiRepository;
import com.cod.dao.WoodRepository;
import com.cod.dto.codi.getfollowingusercodi.GetFollowingUserCodiInput;
import com.cod.dto.codi.getpopularcodi.GetPopularCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.codi.updatecodi.UpdateCodiInput;
import com.cod.dto.user.search.UserSearchOutput;
import com.cod.dto.woodcodi.createwoodcodi.CreateWoodCodiInput;
import com.cod.dto.woodcodi.selectwoodcodi.SelectWoodCodiInput;
import com.cod.dto.woodcodi.selectwoodcodi.SelectWoodCodiOutput;
import com.cod.dto.woodcodi.updatewoodcodi.UpdateWoodCodiInput;
import com.cod.entity.Codi;
import com.cod.entity.User;
import com.cod.entity.Wood;
import com.cod.entity.WoodCodi;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.JwtService;
import com.cod.service.WoodCodiService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.cod.response.ResponseStatus.*;

@Service("WoodCodiService")
@RequiredArgsConstructor
@Slf4j
public class WoodCodiServiceImpl implements WoodCodiService {

    private final WoodRepository woodRepository;
    private final WoodCodiRepository woodCodiRepository;
    private final WoodCodiLikedRepository woodCodiLikedRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createWoodCodi(CreateWoodCodiInput createWoodCodiInput) {
        // 1. 값 형식 체크
        if (createWoodCodiInput == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        }
        if (!ValidationCheck.isValidId(createWoodCodiInput.getWoodId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        if (!ValidationCheck.isValid(createWoodCodiInput.getName())
                || !ValidationCheck.isValid(createWoodCodiInput.getCoordinate())
                || !ValidationCheck.isValid(createWoodCodiInput.getThumbnail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        }

        // 2. 코디나무 코디 생성
        WoodCodi woodiCodi;
        try {
            User loginUser = jwtService.getUser();
            if (loginUser == null)  {
                log.error("[GET]/wood-codies NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }
            Wood wood = woodRepository.findById(createWoodCodiInput.getWoodId()).orElse(null);
            if (wood == null)  {
                log.error("[GET]/wood-codies NOT FOUND WOOD error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_WOOD));
            }
            woodiCodi = WoodCodi.builder()
                    .wood(wood)
                    .user(loginUser)
                    .name(createWoodCodiInput.getName())
                    .thumbnail(createWoodCodiInput.getThumbnail())
                    .coordinate(createWoodCodiInput.getCoordinate())
                    .description(createWoodCodiInput.getDescription())
                    .tag(createWoodCodiInput.getTag())
                    .build();

            woodCodiRepository.save(woodiCodi);

        } catch (Exception e) {
            log.error("[wood-codies/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null, CREATED_WOOD_CODI));
    }


    @Override
    public ResponseEntity<Response<SelectWoodCodiOutput>> selectWoodCodi(int woodCodiId) {
        // 1. 값 형식 체크
        if (!ValidationCheck.isValidId(woodCodiId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        }
        // 2. 코디나무 코디 조회
        WoodCodi woodCodi;
        SelectWoodCodiOutput selectWoodCodiOutput;
        try {
            woodCodi = woodCodiRepository.findById(woodCodiId).orElse(null);
            if(woodCodi==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_WOOD_CODI));
        } catch (Exception e) {
            log.error("[wood-codies/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 최종 출력값 정리
        selectWoodCodiOutput = SelectWoodCodiOutput.builder()
                .woodId(woodCodi.getWood().getId())
                .userId(woodCodi.getUser().getId())
                .codiName(woodCodi.getName())
                .codiTag(woodCodi.getTag())
                .codiThumbnail(woodCodi.getThumbnail())
                .codiCoordinate(woodCodi.getCoordinate())
                .codiDescription(woodCodi.getDescription())
                .codiCreatedAt(woodCodi.getCreated_at())
                .codiUpdatedAt(woodCodi.getUpdated_at())
                .liked(woodCodiLikedRepository.countByWoodCodi(woodCodi))
                .build();

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(selectWoodCodiOutput, SUCCESS_SELECT_WOOD_CODI));
    }

    @Override
    public ResponseEntity<PageResponse<SelectWoodCodiOutput>> selectWoodCodiList(SelectWoodCodiInput selectWoodCodiInput) {
        // 1. 값 형식 체크
        if (selectWoodCodiInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidId(selectWoodCodiInput.getWoodId())
                || !ValidationCheck.isValidPage(selectWoodCodiInput.getPage())
                || !ValidationCheck.isValidId(selectWoodCodiInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));

        // 2. 코디 조회
        Pageable pageable = PageRequest.of(selectWoodCodiInput.getPage() - 1, selectWoodCodiInput.getSize());
        Page<SelectWoodCodiOutput> resultList;
        Page<WoodCodi> woodCodiList;
        try {
            Wood wood = woodRepository.findById(selectWoodCodiInput.getWoodId()).orElse(null);
            if (wood == null)  {
                log.error("[GET]/wood-codies NOT FOUND WOOD error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new PageResponse<>(NOT_FOUND_WOOD));
            }
            woodCodiList = woodCodiRepository.findByWood(wood, pageable);
        } catch (Exception e) {
            log.error("[wood-codies/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 최종 출력값 정리
        resultList = woodCodiList.map(woodCodi -> {
            return SelectWoodCodiOutput.builder()
                    .woodId(woodCodi.getWood().getId())
                    .userId(woodCodi.getUser().getId())
                    .codiName(woodCodi.getName())
                    .codiTag(woodCodi.getTag())
                    .codiThumbnail(woodCodi.getThumbnail())
                    .codiCoordinate(woodCodi.getCoordinate())
                    .codiDescription(woodCodi.getDescription())
                    .codiCreatedAt(woodCodi.getCreated_at())
                    .codiUpdatedAt(woodCodi.getUpdated_at())
                    .liked(woodCodiLikedRepository.countByWoodCodi(woodCodi))
                    .build();
        });
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(resultList, SUCCESS_SELECT_WOOD_CODI));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> updateWoodCodi(UpdateWoodCodiInput updateWoodCodiInput, int woodCodiId) {
        try {
            // 1. 코디나무 코디 조회
            WoodCodi woodCodi = woodCodiRepository.findById(woodCodiId).orElse(null);

            // 2. 코디나무 코디 수정
            if (woodCodi == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));
            if (StringUtils.isNoneBlank(updateWoodCodiInput.getName()))
                woodCodi.setName(updateWoodCodiInput.getName());
            if (StringUtils.isNoneBlank(updateWoodCodiInput.getTag()))
                woodCodi.setTag(updateWoodCodiInput.getTag());
            if (StringUtils.isNoneBlank(updateWoodCodiInput.getDescription()))
                woodCodi.setDescription(updateWoodCodiInput.getDescription());
            if (StringUtils.isNoneBlank(updateWoodCodiInput.getThumbnail()))
                woodCodi.setThumbnail(updateWoodCodiInput.getThumbnail());
            if (StringUtils.isNoneBlank(updateWoodCodiInput.getCoordinate()))
                woodCodi.setCoordinate(updateWoodCodiInput.getCoordinate());
            woodCodiRepository.save(woodCodi);
        } catch (Exception e) {
            log.error("[wood-codies/patch] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_UPDATE_WOOD_CODI));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteWoodCodi(int woodCodiId) {
        try {
            // 1. 코디나무 코디 조회
            WoodCodi woodCodi = woodCodiRepository.findById(woodCodiId).orElse(null);

            // 2. 코디나무 코디 삭제
            if (woodCodi == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));

            woodCodiRepository.delete(woodCodi);

        } catch (Exception e) {
            log.error("[wood-codies/delete] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_DELETE_WOOD_CODI));
    }
}
