package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.FollowRepository;
import com.cod.dao.NoticeRepository;
import com.cod.dao.UserRepository;
import com.cod.dto.follow.createfollow.CreateFollowInput;
import com.cod.dto.follow.deletefollow.DeleteFollowInput;
import com.cod.dto.follow.selectfollow.SelectFollowInput;
import com.cod.dto.notice.NoticeType;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.entity.Follow;
import com.cod.entity.Notice;
import com.cod.entity.User;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.FollowService;
import com.cod.service.JwtService;
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

@Service("FollowService")
@RequiredArgsConstructor
@Slf4j
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final NoticeRepository noticeRepository;

    @Override
    public ResponseEntity<PageResponse<ProfileOutput>> getFollowList(SelectFollowInput selectFollowInput) {
        // 1. 값 형식 체크
        if(selectFollowInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PageResponse<>(NO_VALUES));
        if(!ValidationCheck.isValidPage(selectFollowInput.getPage())
                || !ValidationCheck.isValidId(selectFollowInput.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PageResponse<>(NO_VALUES));
        // 2. 팔로우 리스트 가져오기
        Integer fromUserId = selectFollowInput.getFromUserId();
        Integer toUserId = selectFollowInput.getToUserId();
        Page<ProfileOutput> resultList;
        try {
            Page<Follow> followList;
            if(fromUserId!=null && !userRepository.existsById(fromUserId)) {
                log.error("[follow/get] NOT FOUND FROM USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PageResponse<>(NOT_FOUND_USER));
            }
            if(toUserId!=null && !userRepository.existsById(toUserId)) {
                log.error("[follow/get] NOT FOUND TO USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PageResponse<>(NOT_FOUND_USER));
            }
            if(fromUserId!=null && toUserId!=null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PageResponse<>(BAD_VALUES));

            // 3. 팔로우 리스트에 필요한 최종 결과 가공

            if(fromUserId!=null){ //팔로잉 검색
                Pageable pageable = PageRequest.of(selectFollowInput.getPage() - 1, selectFollowInput.getSize(),
                        Sort.Direction.ASC, "toUser.nickname");;
                followList = followRepository.findByFromUserId(fromUserId, pageable);
                resultList = followList.map(follow -> {
                    User toUser = follow.getToUser();
                    return ProfileOutput.builder()
                            .userId(toUser.getId())
                            .email(toUser.getEmail())
                            .name(toUser.getName())
                            .nickname(toUser.getNickname())
                            .birth(toUser.getBirth())
                            .gender(toUser.getGender())
                            .profile(toUser.getProfile())
                            .introduction(toUser.getIntroduction())
                            .build();
                });
            }
            else { //팔로워 검색
                Pageable pageable = PageRequest.of(selectFollowInput.getPage() - 1, selectFollowInput.getSize(),
                        Sort.Direction.ASC, "fromUser.nickname");;
                followList = followRepository.findByToUserId(toUserId, pageable);
                resultList = followList.map(follow -> {
                    User fromUser = follow.getFromUser();
                    return ProfileOutput.builder()
                            .userId(fromUser.getId())
                            .email(fromUser.getEmail())
                            .name(fromUser.getName())
                            .nickname(fromUser.getNickname())
                            .birth(fromUser.getBirth())
                            .gender(fromUser.getGender())
                            .profile(fromUser.getProfile())
                            .introduction(fromUser.getIntroduction())
                            .build();
                });
            }
        } catch (Exception e) {
            log.error("[follow/get] database error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PageResponse<>(DATABASE_ERROR));
        }
        // 4. 결과 return
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponse<>(resultList,SUCCESS_GET_FOLLOW_LIST));

    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createFollowRelation(CreateFollowInput createFollowInput) {
        // 1. 값 형식 체크
        if (createFollowInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValidId(createFollowInput.getToUserId()))
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(BAD_ID_VALUE));

        // 2. 팔로우 관계 생성
        try {
            int fromUserId = jwtService.getUserId();;
            int toUserId = createFollowInput.getToUserId();

            if (fromUserId == toUserId)
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(BAD_ID_VALUE));

            User fromUser = jwtService.getUser();
            User toUser = userRepository.findById(toUserId).orElse(null);
            if (toUser == null || fromUser == null) {
                log.error("[follow/post] NOT FOUND USER exception");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(BAD_ID_VALUE));
            }
            boolean isExistsFollowRelation = followRepository.existsByFromUserIdAndToUserId(fromUserId, toUserId);
            if (isExistsFollowRelation) {
                log.error("[follow/post] DUPLICATE FOLLOW INFO exception");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(EXISTS_FOLLOW));
            }

            followRepository.save(
                    Follow.builder()
                    .toUser(toUser)
                    .fromUser(fromUser)
                    .build());

            // 신규 팔로우 알림(follow_id, to_user_id)
            Notice notice = Notice.builder()
                    .type(NoticeType.FOLLOW)
                    .receiveUser(toUser)
                    .sendUser(fromUser)
                    .isChecked(false)
                    .message(fromUser.getNickname()+"님이 팔로우 하셨습니다!")
                    .build();
            noticeRepository.save(notice);
        }
        catch (Exception e) {
            log.error("[follow/post] database error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(DATABASE_ERROR));

    }
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(null,CREATED_FOLLOW));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> deleteFollowRelation(DeleteFollowInput deleteFollowInput) {
        // 1. 값 형식 체크
        if (deleteFollowInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValidId(deleteFollowInput.getToUserId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(BAD_ID_VALUE));

        int toUserId = deleteFollowInput.getToUserId();
        // 1. 값 형식 체크
        if (!ValidationCheck.isValidId(toUserId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(BAD_ID_VALUE));
        // 2. 팔로우 관계 삭제
        try {
            User fromUser = jwtService.getUser();
            if (fromUser==null) {
                log.error("[follow/delete] NOT FOUND LOGIN USER exception");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(NOT_FOUND_USER));
            }
            User toUser = userRepository.findById(toUserId).orElse(null);
            if (toUser==null) {
                log.error("[follow/delete] NOT FOUND LOGIN USER exception");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(NOT_FOUND_USER));
            }
            boolean existFollowRelation = followRepository.existsByFromUserIdAndToUserId(fromUser.getId(),toUserId);
            if (!existFollowRelation) {
                log.error("[follow/delete] NOT FOUND FOLLOW RELATION exception");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(NOT_FOUND_FOLLOW));

            }
            followRepository.deleteByFromUserIdAndToUserId(fromUser.getId(), toUserId);
        } catch (Exception e) {
            log.error("[follow/delete] database error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(DATABASE_ERROR));
        }
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(null,SUCCESS_DELETE_FOLLOW));
    }
}
