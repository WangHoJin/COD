package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.CodiLikedRepository;
import com.cod.dao.CodiRepository;
import com.cod.dto.codiliked.createlike.CreateLikeInput;
import com.cod.entity.Codi;
import com.cod.entity.CodiLiked;
import com.cod.response.Response;
import com.cod.service.CodiLikedService;
import com.cod.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.cod.response.ResponseStatus.*;

@Service("CodiLikedService")
@RequiredArgsConstructor
@Slf4j
public class CodiLikedServiceImpl implements CodiLikedService {

    private final CodiLikedRepository codiLikedRepository;
    private final CodiRepository codiRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createLike(CreateLikeInput createLikeInput) {
        // 1. 값 형식 체크
        if (createLikeInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValidId(createLikeInput.getCodiId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        if (!ValidationCheck.isValidId(createLikeInput.getUserId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_CONTENTS));

        // 2. 좋아요 등록
        CodiLiked codiLiked;
        try{
            Codi codi = codiRepository.findById(createLikeInput.getCodiId()).orElse(null);
            codiLiked=CodiLiked.builder()
                    .user(jwtService.getUser())
                    .codi(codi)
                    .build();

        } catch (Exception e){
            log.error("[codi-liked/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null, CREATED_CODI_LIKED));

    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteLike(int id) {
        try{
            // 1. 코디 좋아요 조회
            CodiLiked codiLiked = codiLikedRepository.findByUserAndCodi(id, jwtService.getUserId());

            // 2. 코디 좋아요 취소
            if(codiLiked==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));

            codiLikedRepository.delete(codiLiked);
        } catch (Exception e){
            log.error("[codi-liked/delete] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_DELETE_CODI_LIKED));
    }
}
