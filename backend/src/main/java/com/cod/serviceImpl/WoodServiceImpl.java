package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dao.WoodRepository;
import com.cod.dto.wood.createwood.CreateWoodInput;
import com.cod.entity.User;
import com.cod.entity.Wood;
import com.cod.response.Response;
import com.cod.response.ResponseStatus;
import com.cod.service.JwtService;
import com.cod.service.WoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.cod.response.ResponseStatus.*;

@Service("WoodService")
@RequiredArgsConstructor
@Slf4j
public class WoodServiceImpl implements WoodService {

    private final WoodRepository woodRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<Response<Object>> createWood(CreateWoodInput createWoodInput) {
        // 1. 값 형식 체크
        if(createWoodInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(ResponseStatus.NO_VALUES));
        if(!ValidationCheck.isValid(createWoodInput.getTitle())
                || !ValidationCheck.isValidLocalDate(createWoodInput.getTerminated_at())
                || !ValidationCheck.isValid(createWoodInput.getContent()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(ResponseStatus.NO_VALUES));

        // 2. 코디나무 생성
        try {
            User loginUser = jwtService.getUser();
            if (loginUser == null) {
                log.error("[GET]/woods NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }

            Wood wood = Wood.builder()
                    .user(loginUser)
                    .title(createWoodInput.getTitle())
                    .content(createWoodInput.getContent())
                    .terminated_at(createWoodInput.getTerminated_at())
                    .build();

            woodRepository.save(wood);

        } catch (Exception e) {
            log.error("[woods/post] database error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(null,CREATED_WOOD));
    }
}
