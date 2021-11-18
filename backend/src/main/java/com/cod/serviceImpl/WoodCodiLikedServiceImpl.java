package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.*;
import com.cod.dto.notice.NoticeType;
import com.cod.dto.woodcodiliked.createwoodcodiliked.CreateWoodCodiLikedInput;
import com.cod.dto.woodcodiliked.deletewoodcodiliked.DeleteWoodCodiLikedInput;
import com.cod.entity.*;
import com.cod.response.Response;
import com.cod.service.JwtService;
import com.cod.service.WoodCodiLikedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cod.response.ResponseStatus.*;

@Service("WoodCodiLikedService")
@RequiredArgsConstructor
@Slf4j
public class WoodCodiLikedServiceImpl implements WoodCodiLikedService {

    private final WoodCodiLikedRepository woodCodiLikedRepository;
    private final WoodCodiRepository woodCodiRepository;
    private final JwtService jwtService;
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createWoodCodiLiked(CreateWoodCodiLikedInput createWoodCodiLikedInput) {
        // 1. 값 형식 체크
        if (createWoodCodiLikedInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));

        if (!ValidationCheck.isValidId(createWoodCodiLikedInput.getWoodCodiId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));


        // 2. 코디나무 코디 좋아요 등록
        try{
            User loginUser = jwtService.getUser();
            if (loginUser == null)  {
                log.error("[POST]/wood-codi-liked NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }
            WoodCodi woodCodi = woodCodiRepository.findById(createWoodCodiLikedInput.getWoodCodiId()).orElse(null);
            if (woodCodi == null)  {
                log.error("[POST]/wood-codi-liked NOT FOUND WOOD CODI error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_WOOD_CODI));
            }

            WoodCodiLiked woodCodiLiked=woodCodiLikedRepository.findByWoodCodiAndUser(woodCodi,loginUser);

            if(woodCodiLiked==null){
                woodCodiLikedRepository.save(
                        WoodCodiLiked.builder()
                                .user(loginUser)
                                .woodCodi(woodCodi)
                                .build());
            }
            else{
                log.error("[POST]/wood-codi-liked EXIST WOOD CODI LIKE error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(EXISTS_CODI_LIKE));
            }

            // 신규 좋아요 알림
            Notice notice = Notice.builder()
                    .type(NoticeType.CODI_LIKED)
                    .receiveUser(woodCodi.getUser())
                    .sendUser(loginUser)
                    .isChecked(false)
                    .message(jwtService.getUser().getNickname()+"님이 "+woodCodi.getName()+" 코디를 좋아요 하셨습니다!")
                    .build();
            noticeRepository.save(notice);

        } catch (Exception e){
            log.error("[wood-codi-liked/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null, CREATED_WOOD_CODI_LIKED));

    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteWoodCodiLiked(DeleteWoodCodiLikedInput deleteWoodCodiLikedInput) {
        // 1. 값 형식 체크
        if(deleteWoodCodiLikedInput==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if(!ValidationCheck.isValidId(deleteWoodCodiLikedInput.getWoodCodiId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));

        try{
            // 2. 코디나무 코디 좋아요 조회
            WoodCodi woodCodi=woodCodiRepository.findById(deleteWoodCodiLikedInput.getWoodCodiId()).orElse(null);
            if(woodCodi==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_WOOD_CODI));
            }
            WoodCodiLiked woodCodiLiked = woodCodiLikedRepository.findByWoodCodiAndUser(woodCodi, jwtService.getUser());

            // 3. 코디나무 코디 좋아요 취소
            if(woodCodiLiked==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_VALUES));

            woodCodiLikedRepository.delete(woodCodiLiked);
        } catch (Exception e){
            log.error("[wood-codi-liked/delete] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_DELETE_WOOD_CODI_LIKED));
    }
}
