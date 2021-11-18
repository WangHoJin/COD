package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.ClothRepository;
import com.cod.dao.UserRepository;
import com.cod.dto.cloth.createcloth.CreateClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothOutput;
import com.cod.dto.cloth.updatecloth.UpdateClothInput;
import com.cod.entity.Cloth;
import com.cod.entity.User;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.ClothService;
import com.cod.service.JwtService;
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

@Service("ClothService")
@RequiredArgsConstructor
@Slf4j
public class ClothServiceImpl implements ClothService {

    private final ClothRepository clothRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createCloth(CreateClothInput createClothInput) {
        // 1. 값 형식 체크
        if (createClothInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValid(createClothInput.getName()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));

        // 2. 옷 생성
        Cloth cloth;
        try {
            User loginUser = jwtService.getUser();
            if (loginUser == null)  {
                log.error("[GET]/clothes NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }
            cloth = Cloth.builder()
                    .user(loginUser)
                    .name(createClothInput.getName())
                    .type(createClothInput.getType())
                    .color(createClothInput.getColor())
                    .season(createClothInput.getSeason())
                    .isOwned(createClothInput.getIsOwned())
                    .tag(createClothInput.getTag())
                    .price(createClothInput.getPrice())
                    .brand(createClothInput.getBrand())
                    .measure(createClothInput.getMeasure())
                    .imgUrl(createClothInput.getImgUrl())
                    .build();

            clothRepository.save(cloth);

        } catch (Exception e) {
            log.error("[clothes/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null, CREATED_CLOTH));
    }


    @Override
    public ResponseEntity<Response<SelectClothOutput>> selectCloth(int clothId) {
        // 1. 값 형식 체크
        if (!ValidationCheck.isValidId(clothId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        }
        // 2. 옷 조회
        Cloth cloth;
        SelectClothOutput selectClothOutput;
        try {
            cloth = clothRepository.findById(clothId).orElse(null);
            if(cloth==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_CODI));
        } catch (Exception e) {
            log.error("[clothes/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 최종 출력값 정리
        selectClothOutput = SelectClothOutput.builder()
                .clothId(cloth.getId())
                .userId(cloth.getUser().getId())
                .clothName(cloth.getName())
                .clothTag(cloth.getTag())
                .clothImgUrl(cloth.getImgUrl())
                .clothType(cloth.getType())
                .clothColor(cloth.getColor())
                .clothSeason(cloth.getSeason())
                .clothIsOwned(cloth.getIsOwned())
                .clothBrand(cloth.getBrand())
                .clothPrice(cloth.getPrice())
                .clothMeasure(cloth.getMeasure())
                .clothPrice(cloth.getPrice())
                .clothCreatedAt(cloth.getCreatedAt())
                .clothUpdatedAt(cloth.getUpdatedAt())
                .build();

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(selectClothOutput, SUCCESS_SELECT_CLOTH));
    }

    @Override
    public ResponseEntity<PageResponse<SelectClothOutput>> selectClothList(SelectClothInput selectClothInput, boolean isSearchByType) {
        // 1. 값 형식 체크
        if (selectClothInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidPage(selectClothInput.getPage())
                || !ValidationCheck.isValidId(selectClothInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));

        // 2. 옷 조회
        Pageable pageable = PageRequest.of(selectClothInput.getPage() - 1, selectClothInput.getSize());
        Page<SelectClothOutput> selectClothOutputs;
        User user = userRepository.findById(selectClothInput.getUserId()).orElse(null);
        try {
            Page<Cloth> clothList;
            if(isSearchByType){
                clothList=clothRepository.findByUserAndType(user,selectClothInput.getType(),pageable);
            }
            else{
                clothList=clothRepository.findByUser(user,pageable);
            }
            selectClothOutputs=clothList.map(cloth->{
                return SelectClothOutput.builder()
                        .clothId(cloth.getId())
                        .userId(cloth.getUser().getId())
                        .clothName(cloth.getName())
                        .clothTag(cloth.getTag())
                        .clothImgUrl(cloth.getImgUrl())
                        .clothType(cloth.getType())
                        .clothColor(cloth.getColor())
                        .clothSeason(cloth.getSeason())
                        .clothIsOwned(cloth.getIsOwned())
                        .clothBrand(cloth.getBrand())
                        .clothPrice(cloth.getPrice())
                        .clothMeasure(cloth.getMeasure())
                        .clothCreatedAt(cloth.getCreatedAt())
                        .clothUpdatedAt(cloth.getUpdatedAt())
                        .build();
            });
        } catch (Exception e) {
            log.error("[clothes/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(selectClothOutputs, SUCCESS_SELECT_CLOTH));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> updateCloth(UpdateClothInput updateClothInput, int clothId) {
        try {
            // 1. 옷 조회
            Cloth cloth = clothRepository.findById(clothId).orElse(null);

            // 2. 옷 수정
            if (cloth == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));
            if (StringUtils.isNoneBlank(updateClothInput.getName()))
                cloth.setName(updateClothInput.getName());
            if (StringUtils.isNoneBlank(updateClothInput.getTag()))
                cloth.setTag(updateClothInput.getTag());
            if (StringUtils.isNoneBlank(updateClothInput.getType()))
                cloth.setType(updateClothInput.getType());
            if (StringUtils.isNoneBlank(updateClothInput.getImgUrl()))
                cloth.setImgUrl(updateClothInput.getImgUrl());
            if (StringUtils.isNoneBlank(updateClothInput.getMeasure()))
                cloth.setMeasure(updateClothInput.getMeasure());
            if (StringUtils.isNoneBlank(updateClothInput.getSeason()))
                cloth.setSeason(updateClothInput.getSeason());
            if (StringUtils.isNoneBlank(updateClothInput.getBrand()))
                cloth.setBrand(updateClothInput.getBrand());
            clothRepository.save(cloth);
        } catch (Exception e) {
            log.error("[clothes/patch] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_UPDATE_CODI));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteCloth(int clothId) {
        try {
            // 1. 옷 조회
            Cloth cloth = clothRepository.findById(clothId).orElse(null);

            // 2. 옷 삭제
            if (cloth == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));

            clothRepository.delete(cloth);

        } catch (Exception e) {
            log.error("[clothes/delete] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_DELETE_CODI));
    }
}
