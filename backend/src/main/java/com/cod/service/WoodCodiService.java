package com.cod.service;

import com.cod.dto.codi.createcodi.CreateCodiInput;
import com.cod.dto.codi.getfollowingusercodi.GetFollowingUserCodiInput;
import com.cod.dto.codi.getpopularcodi.GetPopularCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.codi.updatecodi.UpdateCodiInput;
import com.cod.dto.woodcodi.createwoodcodi.CreateWoodCodiInput;
import com.cod.dto.woodcodi.selectwoodcodi.SelectWoodCodiInput;
import com.cod.dto.woodcodi.selectwoodcodi.SelectWoodCodiOutput;
import com.cod.dto.woodcodi.updatewoodcodi.UpdateWoodCodiInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface WoodCodiService {
    ResponseEntity<Response<Object>> createWoodCodi(CreateWoodCodiInput createWoodCodiInput);
    ResponseEntity<Response<SelectWoodCodiOutput>> selectWoodCodi(int woodCodiId);
    ResponseEntity<PageResponse<SelectWoodCodiOutput>> selectWoodCodiList(SelectWoodCodiInput selectWoodCodiInput);
    ResponseEntity<Response<Object>> updateWoodCodi(UpdateWoodCodiInput updateWoodCodiInput, int woodCodiId);
    ResponseEntity<Response<Object>> deleteWoodCodi(int woodCodiId);
}
