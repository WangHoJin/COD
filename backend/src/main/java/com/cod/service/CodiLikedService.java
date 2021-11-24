package com.cod.service;

import com.cod.dto.codiliked.createcodiliked.CreateCodiLikedInput;
import com.cod.dto.codiliked.deletecodiliked.DeleteCodiLiked;
import com.cod.dto.codiliked.selectcodilikedlist.SelectCodiLikedListInput;
import com.cod.dto.codiliked.selectcodilikedlist.SelectCodiLikedListOutput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface CodiLikedService {
    ResponseEntity<Response<Object>> createCodiLiked(CreateCodiLikedInput createCodiLikedInput);
    ResponseEntity<Response<Object>> deleteCodiLiked(DeleteCodiLiked deleteCodiLiked);
    ResponseEntity<PageResponse<SelectCodiLikedListOutput>> selectCodiLikedList(SelectCodiLikedListInput selectCodiLikedListInput);
}
