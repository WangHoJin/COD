package com.cod.service;

import com.cod.dto.woodcodiliked.createwoodcodiliked.CreateWoodCodiLikedInput;
import com.cod.dto.woodcodiliked.deletewoodcodiliked.DeleteWoodCodiLikedInput;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface WoodCodiLikedService {
    ResponseEntity<Response<Object>> createWoodCodiLiked(CreateWoodCodiLikedInput createWoodCodiLikedInput);
    ResponseEntity<Response<Object>> deleteWoodCodiLiked(DeleteWoodCodiLikedInput deleteWoodCodiLikedInput);
}
