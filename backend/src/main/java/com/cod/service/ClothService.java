package com.cod.service;

import com.cod.dto.cloth.createcloth.CreateClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothOutput;
import com.cod.dto.cloth.updatecloth.UpdateClothInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClothService {
    ResponseEntity<Response<Object>> createCloth(CreateClothInput createClothInput);
    ResponseEntity<Response<SelectClothOutput>> selectCloth(int clothId);
    ResponseEntity<PageResponse<SelectClothOutput>> selectClothList(SelectClothInput selectClothInput,boolean isSearchByType);
    ResponseEntity<Response<Object>> updateCloth(UpdateClothInput updateClothInput, int clothId);
    ResponseEntity<Response<Object>> deleteCloth(int clothId);
}