package com.cod.service;

import com.cod.dto.grade.selectgrade.SelectGradeOutput;
import com.cod.dto.grade.updategrade.UpdateGradeInput;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface GradeService {
    ResponseEntity<Response<SelectGradeOutput>> selectGrade(int userId);
    ResponseEntity<Response<Object>> updateGrade(int userId, UpdateGradeInput updateGradeInput);
}
