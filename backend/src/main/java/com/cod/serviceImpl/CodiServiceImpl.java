package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.CodiLikedRepository;
import com.cod.dao.CodiRepository;
import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.dto.codi.getfollowingusercodi.GetFollowingUserCodiInput;
import com.cod.dto.codi.getpopularcodi.GetPopularCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.codi.updatecodi.UpdateCodiInput;
import com.cod.entity.Codi;
import com.cod.entity.User;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.CodiService;
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

@Service("CodiService")
@RequiredArgsConstructor
@Slf4j
public class CodiServiceImpl implements CodiService {

    private final CodiRepository codiRepository;
    private final CodiLikedRepository codiLikedRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createCodi(CreateCodiInput createCodiInput) {
        // 1. 값 형식 체크
        if (createCodiInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValid(createCodiInput.getName())
                || !ValidationCheck.isValid(createCodiInput.getCoordinate())
                || !ValidationCheck.isValid(createCodiInput.getThumbnail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));

        // 2. 코디 생성
        Codi codi;
        try {
            User loginUser = jwtService.getUser();
            if (loginUser == null)  {
                log.error("[GET]/codies NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }
            codi = Codi.builder()
                    .user(loginUser)
                    .name(createCodiInput.getName())
                    .thumbnail(createCodiInput.getThumbnail())
                    .coordinate(createCodiInput.getCoordinate())
                    .description(createCodiInput.getDescription())
                    .tag(createCodiInput.getTag())
                    .build();

            codiRepository.save(codi);

        } catch (Exception e) {
            log.error("[codies/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null, CREATED_CODI));
    }


    @Override
    public ResponseEntity<Response<SelectCodiOutput>> selectCodi(int codiId) {
        // 1. 값 형식 체크
        if (!ValidationCheck.isValidId(codiId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        }
        // 2. 코디 조회
        Codi codi;
        SelectCodiOutput selectCodiOutput;
        try {
            codi = codiRepository.findById(codiId).orElse(null);
            if(codi==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_CODI));
        } catch (Exception e) {
            log.error("[codies/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 최종 출력값 정리
        selectCodiOutput = SelectCodiOutput.builder()
                .userId(codi.getUser().getId())
                .codiName(codi.getName())
                .codiTag(codi.getTag())
                .codiThumbnail(codi.getThumbnail())
                .codiCoordinate(codi.getCoordinate())
                .codiDescription(codi.getDescription())
                .codiCreatedAt(codi.getCreatedAt())
                .codiUpdatedAt(codi.getUpdatedAt())
                .liked(codiLikedRepository.countByCodi(codi))
                .build();

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(selectCodiOutput, SUCCESS_SELECT_CODI));
    }

    @Override
    public ResponseEntity<PageResponse<SelectCodiOutput>> selectCodiList(SelectCodiInput selectCodiInput) {
        // 1. 값 형식 체크
        if (selectCodiInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidPage(selectCodiInput.getPage())
                || !ValidationCheck.isValidId(selectCodiInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));

        // 2. 코디 조회
        Pageable pageable = PageRequest.of(selectCodiInput.getPage() - 1, selectCodiInput.getSize());
        Page<SelectCodiOutput> selectCodiOutputs;
        try {
            selectCodiOutputs = codiRepository.findByDynamicQuery(selectCodiInput, pageable);
        } catch (Exception e) {
            log.error("[codies/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(selectCodiOutputs, SUCCESS_SELECT_CODI));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> updateCodi(UpdateCodiInput updateCodiInput, int codiId) {
        try {
            // 1. 코디 조회
            Codi codi = codiRepository.findById(codiId).orElse(null);

            // 2. 코디 수정
            if (codi == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));
            if (StringUtils.isNoneBlank(updateCodiInput.getName()))
                codi.setName(updateCodiInput.getName());
            if (StringUtils.isNoneBlank(updateCodiInput.getTag()))
                codi.setTag(updateCodiInput.getTag());
            if (StringUtils.isNoneBlank(updateCodiInput.getDescription()))
                codi.setDescription(updateCodiInput.getDescription());
            if (StringUtils.isNoneBlank(updateCodiInput.getThumbnail()))
                codi.setThumbnail(updateCodiInput.getThumbnail());
            if (StringUtils.isNoneBlank(updateCodiInput.getCoordinate()))
                codi.setCoordinate(updateCodiInput.getCoordinate());
            codiRepository.save(codi);
        } catch (Exception e) {
            log.error("[codies/patch] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_UPDATE_CODI));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteCodi(int codiId) {
        try {
            // 1. 코디 조회
            Codi codi = codiRepository.findById(codiId).orElse(null);

            // 2. 코디 삭제
            if (codi == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));

            codiRepository.delete(codi);

        } catch (Exception e) {
            log.error("[codies/delete] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_DELETE_CODI));
    }

    @Override
    public ResponseEntity<PageResponse<SelectCodiOutput>> getPopularCodi(GetPopularCodiInput getPopularCodiInput) {
        // 포맷터
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 문자열 -> Date
        LocalDateTime start = LocalDate.parse(getPopularCodiInput.getStartDate(), formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(getPopularCodiInput.getEndDate(), formatter)
                .atStartOfDay()
                .plusDays(1)
                .minusSeconds(1);
        System.out.println(start+"\n"+end);

        // 1. 값 형식 체크
        if (getPopularCodiInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidPage(getPopularCodiInput.getPage())
                || !ValidationCheck.isValidId(getPopularCodiInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));

        // 2. 코디 조회
        Pageable pageable = PageRequest.of(getPopularCodiInput.getPage() - 1, getPopularCodiInput.getSize());
        Page<SelectCodiOutput> selectCodiOutputs;
        try {
            selectCodiOutputs = codiRepository.getPopularCodi(start, end, pageable);
        } catch (Exception e) {
            log.error("[codies/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(selectCodiOutputs, SUCCESS_SELECT_CODI));
    }

    @Override
    public ResponseEntity<PageResponse<SelectCodiOutput>> getFollowingUserCodi(GetFollowingUserCodiInput getFollowingUserCodiInput) {
        // 1. 값 형식 체크
        if (getFollowingUserCodiInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidPage(getFollowingUserCodiInput.getPage())
                || !ValidationCheck.isValidId(getFollowingUserCodiInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));

        // 2. 코디 조회
        Pageable pageable = PageRequest.of(getFollowingUserCodiInput.getPage() - 1, getFollowingUserCodiInput.getSize());
        Page<SelectCodiOutput> selectCodiOutputs;
        try {
            User loginUser = jwtService.getUser();
            if (loginUser == null)  {
                log.error("[GET]/codies NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new PageResponse<>(NOT_FOUND_USER));
            }
            selectCodiOutputs = codiRepository.getFollowingUserCodi(loginUser.getId(), pageable);
        } catch (Exception e) {
            log.error("[codies/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(selectCodiOutputs, SUCCESS_SELECT_CODI));
    }
}
