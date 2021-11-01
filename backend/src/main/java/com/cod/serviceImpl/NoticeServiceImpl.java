package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.CodiLikedRepository;
import com.cod.dao.CodiRepository;
import com.cod.dao.NoticeRepository;
import com.cod.dao.UserRepository;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.notice.NoticeType;
import com.cod.dto.notice.createnotice.CreateNoticeInput;
import com.cod.dto.notice.selectnotice.SelectNoticeInput;
import com.cod.dto.notice.selectnotice.SelectNoticeOutput;
import com.cod.dto.notice.updatenotice.UpdateNotionInput;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.entity.Codi;
import com.cod.entity.Notice;
import com.cod.entity.User;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.JwtService;
import com.cod.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cod.response.ResponseStatus.*;

@Service("NoticeService")
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final CodiRepository codiRepository;
    private final CodiLikedRepository codiLikedRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createNotice(CreateNoticeInput createNoticeInput) {
        // 1. 값 형식 체크
        if (createNoticeInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValidNoticeType(createNoticeInput.getType()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_NOTICE_TYPE_VALUE));
        if (!ValidationCheck.isValidId(createNoticeInput.getReceiveUserId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        if (!ValidationCheck.isValid(createNoticeInput.getMessage()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_CONTENTS));

        // 2. 알림 생성
        Notice notice;
        try {
            User receiver = userRepository.findById(createNoticeInput.getReceiveUserId()).orElse(null);
            if (receiver == null)  {
                log.error("[GET]/notice NOT FOUND RECEIVE USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }
            User sender = userRepository.findById(createNoticeInput.getSendUserId()).orElse(null);
            Codi codi = codiRepository.findById(createNoticeInput.getCodiId()).orElse(null);
            notice = Notice.builder()
                    .type(NoticeType.valueOf(createNoticeInput.getType()))
                    .receiveUser(receiver)
                    .sendUser(sender)
                    .message(createNoticeInput.getMessage())
                    .codi(codi)
                    .isChecked(false)
                    .build();

            noticeRepository.save(notice);

        } catch (Exception e) {
            log.error("[notice/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null, CREATED_NOTICE));
    }

    @Override
    public ResponseEntity<PageResponse<SelectNoticeOutput>> selectNoticeList(SelectNoticeInput selectNoticeInput) {
        // 1. 값 형식 체크
        if (selectNoticeInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        if (!ValidationCheck.isValidPage(selectNoticeInput.getPage())
                || !ValidationCheck.isValidId(selectNoticeInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));

        // 2. 알림 조회
        Pageable pageable = PageRequest.of(selectNoticeInput.getPage() - 1, selectNoticeInput.getSize(),
                Sort.Direction.DESC, "createdAt");
        Page<SelectNoticeOutput> responseList;
        Page<Notice> noticeList;
        try {
            User loginUser = jwtService.getUser();
            if (loginUser == null) {
                log.error("[GET]/notice NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new PageResponse<>(NOT_FOUND_USER));
            }
            noticeList = noticeRepository.findByReceiveUser(loginUser, pageable);
        } catch (Exception e) {
            log.error("[notice/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }

        // 최종 출력값 정리
        responseList = noticeList.map(notice -> {
            ProfileOutput rSender = null;
            User sender = notice.getSendUser();
            if(sender!=null)
                rSender = ProfileOutput.builder()
                        .email(sender.getEmail())
                        .name(sender.getName())
                        .nickname(sender.getNickname())
                        .birth(sender.getBirth())
                        .gender(sender.getGender())
                        .profile(sender.getProfile())
                        .introduction(sender.getIntroduction())
                        .build();
            SelectCodiOutput rCodi = null;
            Codi codi = notice.getCodi();
            if(codi!=null)
                rCodi = SelectCodiOutput.builder()
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
            return SelectNoticeOutput.builder()
                    .noticeId(notice.getId())
                    .noticeType(notice.getType().name())
                    .noticeMessage(notice.getMessage())
                    .noticeCreatedAt(notice.getCreatedAt())
                    .noticeIsChecked(notice.isChecked())
                    .sendUser(rSender)
                    .codi(rCodi)
                    .build();
        });

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(responseList, SUCCESS_SELECT_NOTICE));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> updateNotice(UpdateNotionInput updateNoticeInput, int noticeId) {
        // 1. 값 형식 체크
        if (updateNoticeInput == null || updateNoticeInput.getIsChecked() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        try {
            // 1. 알림 조회
            Notice notice = noticeRepository.findById(noticeId).orElse(null);

            // 2. 알림 수정
            if (notice == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));
            notice.setChecked(updateNoticeInput.getIsChecked());
            noticeRepository.save(notice);
        } catch (Exception e) {
            log.error("[notice/patch] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_UPDATE_NOTICE));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteNotice(int noticeId) {
        try {
            // 1. 알림 조회
            Notice notice = noticeRepository.findById(noticeId).orElse(null);

            // 2. 알림 삭제
            if (notice == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(BAD_ID_VALUE));

            noticeRepository.delete(notice);

        } catch (Exception e) {
            log.error("[notice/delete] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_DELETE_NOTICE));
    }

    @Override
    public ResponseEntity<Response<Boolean>> checkNewNotice() {
        User loginUser = jwtService.getUser();
        if(loginUser==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NOT_FOUND_USER));
        boolean hasNew = noticeRepository.countNewNotice(loginUser.getId())>0?true:false;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Response<>(hasNew,SUCCESS_CHECK_NOTICE));
    }
}
