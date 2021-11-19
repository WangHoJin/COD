package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.GradeRepository;
import com.cod.dao.UserRepository;
import com.cod.dto.grade.selectgrade.SelectGradeOutput;
import com.cod.dto.grade.updategrade.UpdateGradeInput;
import com.cod.entity.Grade;
import com.cod.entity.User;
import com.cod.response.Response;
import com.cod.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cod.response.ResponseStatus.*;

@Service("GradeService")
@RequiredArgsConstructor
@Slf4j
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Response<SelectGradeOutput>> selectGrade(int userId) {
        // 1. 값 형식 체크
        if (!ValidationCheck.isValidId(userId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_ID_VALUE));
        }
        // 2. 업적 조회
        Grade grade;
        SelectGradeOutput selectGradeOutput;
        try {
            grade = gradeRepository.findByUserId(userId);
            if (grade == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_GRADE));
        } catch (Exception e) {
            log.error("[grades/get] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 출력값 정리
        selectGradeOutput = SelectGradeOutput.builder()
                .point(grade.getPoint())
                .build();

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(selectGradeOutput, SUCCESS_SELECT_GRADE));
    }

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> updateGrade(int userId, UpdateGradeInput updateGradeInput) {
        try {
            // 1. 업적 조회
            Grade grade = gradeRepository.findByUserId(userId);
            if (grade == null) {
                User user = userRepository.findById(userId).get();
                grade = Grade.builder()
                        .user(user)
                        .point(updateGradeInput.getPoint())
                        .build();

            } else grade.setPoint(grade.getPoint() + updateGradeInput.getPoint());
            // 2. 업적 수정
            gradeRepository.save(grade);
        } catch (Exception e) {
            log.error("[grades/patch] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 출력
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null, SUCCESS_UPDATE_COMMENT));
    }
}
