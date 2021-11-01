package com.cod.service;

import com.cod.dto.codiliked.createcodiliked.CreateCodiLikedInput;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface CodiLikedService {
    ResponseEntity<Response<Object>> createCodiLiked(CreateCodiLikedInput createCodiLikedInput);
    ResponseEntity<Response<Object>> deleteCodiLiked(int codiId);
}
