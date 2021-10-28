package com.cod.service;

import com.cod.dto.wood.createwood.CreateWoodInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface WoodService {
    ResponseEntity<Response<Object>> createWood(CreateWoodInput createWoodInput);
}
