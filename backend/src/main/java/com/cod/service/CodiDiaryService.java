package com.cod.service;

import com.cod.dto.codidiary.createcodidiary.CreateCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryOutput;
import com.cod.dto.codidiary.updatecodidiary.UpdateCodiDiaryInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface DiaryService {
    ResponseEntity<Response<Object>> createCodiDiary(CreateCodiDiaryInput createCodiInput);
    ResponseEntity<Response<SelectCodiDiaryOutput>> selectCodiDiary(int codiId);
    ResponseEntity<PageResponse<SelectCodiDiaryOutput>> selectCodiListDiary(SelectCodiDiaryInput selectCodiInput);
    ResponseEntity<Response<Object>> updateCodiDiary(UpdateCodiDiaryInput updateCodiDiaryInput, int codiId);
    ResponseEntity<Response<Object>> deleteCodiDiary(int codiId);
}
