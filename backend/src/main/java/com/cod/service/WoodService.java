package com.cod.service;

import com.cod.dto.wood.createwood.CreateWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodOutput;
import com.cod.dto.wood.updatewood.UpdateWoodInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface WoodService {
    ResponseEntity<Response<Object>> createWood(CreateWoodInput createWoodInput);
    ResponseEntity<PageResponse<SelectWoodOutput>> selectWoodList(SelectWoodInput selectWoodInput);
    ResponseEntity<Response<Object>> updateWood(int woodId, UpdateWoodInput updateWoodInput);
}
