package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.CodiRepository;
import com.cod.dao.CommentRepository;
import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.entity.Codi;
import com.cod.entity.Comment;
import com.cod.entity.User;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.response.ResponseStatus;
import com.cod.service.CodiService;
import com.cod.service.CommentService;
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

import static com.cod.response.ResponseStatus.*;

@Service("CommentService")
@RequiredArgsConstructor
@Slf4j
public class CodiServiceImpl implements CodiService {

    private final CodiRepository codiRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createCodi(CreateCodiInput createCodiInput) {
        // 1. 값 형식 체크
        if (createCodiInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValidId(createCodiInput.getUser_id())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        }
        if (!ValidationCheck.isValid(createCodiInput.getName())
                || !ValidationCheck.isValid(createCodiInput.getCoordinate())
                || !ValidationCheck.isValid(createCodiInput.getThumbnail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));

        // 2. 코디 생성
        Codi codi;
        try {
            codi = Codi.builder()
                    .user(User.builder().id(1).name("민정").build())
//                    .user(jwtService.getUser())
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

//    @Override
//    public ResponseEntity<PageResponse<SelectCommentOutput>> selectComment(SelectCommentInput selectCommentInput) {
//        // 1. 값 형식 체크
//        if (selectCommentInput == null)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new PageResponse<>(NO_VALUES));
//
//        // 2. 댓글 조회
//        Pageable pageable = PageRequest.of(selectCommentInput.getPage() - 1, selectCommentInput.getSize());
//        Page<SelectCommentOutput> responseList;
//        Page<Comment> commentList;
//        try {
//            Codi codi = codiRepository.findById(selectCommentInput.getCodiId()).orElse(null);
//            if(codi==null)
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new PageResponse<>(NOT_FOUND_CODI));
//           commentList = commentRepository.findByCodi(codi, pageable);
//
//
//        } catch (Exception e) {
//            log.error("[comments/get] database error", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new PageResponse<>(DATABASE_ERROR));
//        }
//
//        // 최종 출력값 정리
//        responseList = commentList.map(comment -> {
//            return SelectCommentOutput.builder()
//                    .codiId(comment.getCodi().getId())
//                    .userId(comment.getUser().getId())
//                    .commentContent(comment.getContent())
//                    .commentCreatedAt(comment.getCreated_at())
//                    .commentUpdatedAt(comment.getUpdated_at())
//                    .build();
//        });
//
//        // 3. 결과 return
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new PageResponse<>(responseList, SUCCESS_SELECT_COMMENT));
//    }
//
//    @Override
//    @Transactional
//    public ResponseEntity<Response<Object>> updateComment(UpdateCommentInput updateCommentInput, int commentId) {
//        try {
//            // 1. 댓글 조회
//            Comment comment = commentRepository.findById(commentId).orElse(null);
//
//            // 2. 댓글 수정
//            if (comment == null)
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new Response<>(BAD_ID_VALUE));
//            if (StringUtils.isBlank(updateCommentInput.getContent()))
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new Response<>(NO_CONTENTS));
//            comment.setContent(updateCommentInput.getContent());
//            commentRepository.save(comment);
//        } catch (Exception e) {
//            log.error("[comments/patch] database error", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new Response<>(DATABASE_ERROR));
//        }
//        // 3. 결과 return
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new Response<>(null, SUCCESS_UPDATE_COMMENT));
//    }
//
//    @Override
//    @Transactional
//    public ResponseEntity<Response<Object>> deleteComment(int commentId) {
//        try {
//            // 1. 댓글 조회
//            Comment comment = commentRepository.findById(commentId).orElse(null);
//
//            // 2. 댓글 삭제
//            if (comment == null)
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new Response<>(BAD_ID_VALUE));
//
//            commentRepository.delete(comment);
//
//        } catch (Exception e) {
//            log.error("[comments/delete] database error", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new Response<>(DATABASE_ERROR));
//        }
//
//        // 3. 결과 return
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new Response<>(null, SUCCESS_DELETE_COMMENT));
//    }
}
