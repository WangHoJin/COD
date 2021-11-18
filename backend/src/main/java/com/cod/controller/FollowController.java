package com.cod.controller;

import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.dto.follow.createfollow.CreateFollowInput;
import com.cod.dto.follow.deletefollow.DeleteFollowInput;
import com.cod.dto.follow.selectfollow.SelectFollowInput;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.CommentService;
import com.cod.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
@Slf4j
public class FollowController {

    private final FollowService followService;

    /**
     * 팔로우 관계 등록 API [POST] /api/follow
     *
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PostMapping
    public ResponseEntity<Response<Object>> createFollow(@RequestBody CreateFollowInput createFollowInput) {
        log.info("[POST] /api/follows");
        return followService.createFollowRelation(createFollowInput);
    }

    /**
     * 팔로워 or 팔로잉 조회 API
     * [GET] /api/follows?fromUserId=&page=&size=
     * [GET] /api/follows?toUserId=&page=&size=
     * @return ResponseEntity<PageResponse<ProfileOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<ProfileOutput>> getFollowList(SelectFollowInput selectFollowInput) {
        log.info("[GET] /api/follows?fromUserId=&page=&size=");
        return followService.getFollowList(selectFollowInput);
    }

    /**
     * 팔로우 관계 삭제 API
     * [DELETE] /api/follows?
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping
    public ResponseEntity<Response<Object>> deleteComment(DeleteFollowInput deleteFollowInput) {
        log.info("[DELETE] /api/follows?toUserId=");
        return followService.deleteFollowRelation(deleteFollowInput);
    }

}

