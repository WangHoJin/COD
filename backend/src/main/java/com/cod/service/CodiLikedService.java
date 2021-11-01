package com.cod.service;

import com.cod.dto.codiliked.createlike.CreateLikeInput;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface CodiLikedService {
    ResponseEntity<Response<Object>> createLike(CreateLikeInput createLikeInput);
    ResponseEntity<Response<Object>> deleteLike(int id);
}
