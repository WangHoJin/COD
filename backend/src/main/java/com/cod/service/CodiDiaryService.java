package com.cod.service;

import com.cod.dto.codidiary.createcodidiary.CreateCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryOutput;
import com.cod.dto.codidiary.updatecodidiary.UpdateCodiDiaryInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface CodiDiaryService {
    ResponseEntity<Response<Object>> createCodiDiary(CreateCodiDiaryInput createCodiDiaryInput);
    ResponseEntity<Response<SelectCodiDiaryOutput>> selectCodiDiary(int codiDiaryId);
    ResponseEntity<PageResponse<SelectCodiDiaryOutput>> selectCodiDiaryList(SelectCodiDiaryInput selectCodiDiaryInput);
    ResponseEntity<Response<Object>> updateCodiDiary(UpdateCodiDiaryInput updateCodiDiaryInput, int codiDiaryId);
    ResponseEntity<Response<Object>> deleteCodiDiary(int codiDiaryId);
}
