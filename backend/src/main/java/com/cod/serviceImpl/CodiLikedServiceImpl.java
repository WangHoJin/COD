package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.CodiLikedRepository;
import com.cod.dao.CodiRepository;
import com.cod.dao.NoticeRepository;
import com.cod.dto.codiliked.createcodiliked.CreateCodiLikedInput;
import com.cod.dto.codiliked.deletecodiliked.DeleteCodiLiked;
import com.cod.dto.codiliked.selectcodilikedlist.SelectCodiLikedListInput;
import com.cod.dto.codiliked.selectcodilikedlist.SelectCodiLikedListOutput;
import com.cod.dto.notice.NoticeType;
import com.cod.entity.*;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.CodiLikedService;
import com.cod.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createCodiLiked(CreateCodiLikedInput createCodiLikedInput) {
        // 1. 값 형식 체크
        if (createCodiLikedInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));

        if (!ValidationCheck.isValidId(createCodiLikedInput.getCodiId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));


        // 2. 코디 좋아요 등록
        try{
            User loginUser = jwtService.getUser();
            if (loginUser == null)  {
                log.error("[POST]/codi-liked NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }
            Codi codi = codiRepository.findById(createCodiLikedInput.getCodiId()).orElse(null);
            if (codi == null)  {
                log.error("[POST]/codi-liked NOT FOUND CODI error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_CODI));
            }

            CodiLiked codiLiked=codiLikedRepository.findByCodiAndUser(codi,loginUser);

            if(codiLiked==null){
                codiLikedRepository.save(
                        CodiLiked.builder()
                                .user(jwtService.getUser())
                                .codi(codi)
                                .build());
            }
            else{
                log.error("[POST]/codi-liked EXIST CODI LIKE error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(EXISTS_CODI_LIKE));
            }

            // 신규 좋아요 알림
            Notice notice = Notice.builder()
                    .type(NoticeType.CODI_LIKED)
                    .receiveUser(codi.getUser())
                    .sendUser(jwtService.getUser())
                    .isChecked(false)
                    .message(jwtService.getUser().getNickname()+"님이 "+codi.getName()+" 코디를 좋아요 하셨습니다!")
                    .build();
            noticeRepository.save(notice);

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
    public ResponseEntity<Response<Object>> deleteCodiLiked(DeleteCodiLiked deleteCodiLiked) {
        // 1. 값 형식 체크
        if(deleteCodiLiked==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if(!ValidationCheck.isValidId(deleteCodiLiked.getCodiId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));

        try{
            // 2. 코디 좋아요 조회
            Codi codi=codiRepository.findById(deleteCodiLiked.getCodiId()).orElse(null);
            if(codi==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_CODI));
            }
            CodiLiked codiLiked = codiLikedRepository.findByCodiAndUser(codi, jwtService.getUser());

            // 3. 코디 좋아요 취소
            if(codiLiked==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_VALUES));

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

    @Override
    public ResponseEntity<PageResponse<SelectCodiLikedListOutput>> selectCodiLikedList(SelectCodiLikedListInput selectCodiLikedListInput) {
        // 1. 값 형식 체크
        if (selectCodiLikedListInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidPage(selectCodiLikedListInput.getPage())
                || !ValidationCheck.isValidId(selectCodiLikedListInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(BAD_VALUES));

        Page<SelectCodiLikedListOutput> selectCodiLikedListOutputs;
        try {
            // 2. 좋아요 누른 코디 조회
            Pageable pageable = PageRequest.of(selectCodiLikedListInput.getPage() - 1, selectCodiLikedListInput.getSize());
            Page<CodiLiked> codiLikedList=codiLikedRepository.findByUser(jwtService.getUser(),pageable);

            // 3. 좋아요 누른 코디 리스트에 필요한 최종 결과 가공
            selectCodiLikedListOutputs = codiLikedList.map(codiLiked -> {
                return SelectCodiLikedListOutput.builder()
                        .codiId(codiLiked.getCodi().getId())
                        .codiThumbnail(codiLiked.getCodi().getThumbnail())
                        .build();
            });
        } catch (Exception e) {
            log.error("[codi-liked/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 4. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(selectCodiLikedListOutputs, SUCCESS_SELECT_CODI_LIKED));

    }
}
