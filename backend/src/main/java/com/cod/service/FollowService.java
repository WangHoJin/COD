package com.cod.service;

import com.cod.dto.follow.createfollow.CreateFollowInput;
import com.cod.dto.follow.deletefollow.DeleteFollowInput;
import com.cod.dto.follow.selectfollow.SelectFollowInput;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface FollowService {
    ResponseEntity<PageResponse<ProfileOutput>> getFollowList(SelectFollowInput selectFollowInput);
    ResponseEntity<Response<Object>> createFollowRelation(CreateFollowInput createFollowInput);
    ResponseEntity<Response<Object>> deleteFollowRelation(DeleteFollowInput deleteFollowInput);
}
