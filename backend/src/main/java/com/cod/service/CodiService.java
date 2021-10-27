package com.cod.service;

import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.dto.codi.getfollowingusercodi.GetFollowingUserCodiInput;
import com.cod.dto.codi.getpopularcodi.GetPopularCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.codi.updatecodi.UpdateCodiInput;
import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CodiService {
    ResponseEntity<Response<Object>> createCodi(CreateCodiInput createCodiInput);
    ResponseEntity<Response<SelectCodiOutput>> selectCodi(int codiId);
    ResponseEntity<PageResponse<SelectCodiOutput>> selectCodiList(SelectCodiInput selectCodiInput);
    ResponseEntity<Response<Object>> updateCodi(UpdateCodiInput updateCodiInput, int codiId);
    ResponseEntity<Response<Object>> deleteCodi(int codiId);
    ResponseEntity<PageResponse<SelectCodiOutput>> getPopularCodi(GetPopularCodiInput getPopularCodiInput);
    ResponseEntity<PageResponse<SelectCodiOutput>> getFollowingUserCodi(GetFollowingUserCodiInput getFollowingUserCodiInput);
}
