package com.cod.serviceImpl;

import com.cod.configuration.AES128;
import com.cod.configuration.ValidationCheck;
import com.cod.dao.GradeRepository;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.dto.user.profile.ProfileUpdate;
import com.cod.dto.user.search.UserSearchInput;
import com.cod.dto.user.search.UserSearchOutput;
import com.cod.dto.user.signin.SignInInput;
import com.cod.dto.user.signup.SignUpInput;
import com.cod.entity.Grade;
import com.cod.entity.User;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.response.ResponseStatus;
import com.cod.service.JwtService;
import com.cod.service.UserService;
import com.cod.dao.UserRepository;
import com.cod.dto.user.signin.SignInOutput;
import com.cod.dto.user.signup.SignUpOutput;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.cod.response.ResponseStatus.*;

@Service("UserService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final JwtService jwtService;

    @Value("${custom.constant.user.info.password.key}")
    private String USER_INFO_PASSWORD_KEY;

    @Override
    public ResponseEntity<Response<SignInOutput>> signIn(SignInInput signInInput) {
        // 1. 값 형식 체크
        if (signInInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValid(signInInput.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Response<>(BAD_EMAIL_VALUE));
        if (!ValidationCheck.isValid(signInInput.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_PASSWORD_VALUE));

        // 2. user 정보 가져오기
        User user;
        try {
            String email = signInInput.getEmail();
            String password = new AES128(USER_INFO_PASSWORD_KEY).encrypt(signInInput.getPassword());
            user = userRepository.findByEmail(email).orElse(null);
            if (user==null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            } else if (!user.getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(FAILED_TO_SIGN_IN));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. access token 생성
        String accessToken;
        try{
            accessToken = jwtService.createAccessToken(user.getId());
            if (accessToken.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(FAILED_TO_CREATE_TOKEN));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(FAILED_TO_CREATE_TOKEN));
        }

        // 4. 결과 return
        SignInOutput signInOutput = new SignInOutput(user.getId(), accessToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(signInOutput, SUCCESS_SIGN_IN));
    }

    @Override
    public ResponseEntity<Response<SignUpOutput>> signUp(SignUpInput signUpInput) {
        // 1. 값 형식 체크
        System.out.println(signUpInput);
        System.out.println("버스 : " + signUpInput.getBirth());
        if (signUpInput == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(NO_VALUES));
        if (!ValidationCheck.isValid(signUpInput.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_EMAIL_VALUE));
        if (!ValidationCheck.isValid(signUpInput.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_PASSWORD_VALUE));
        if (!ValidationCheck.isValid(signUpInput.getName()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_NAME_VALUE));
        if (!ValidationCheck.isValid(signUpInput.getNickname()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_NICKNAME_VALUE));
        if (!ValidationCheck.isValidLocalDate(signUpInput.getBirth()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_BIRTH_VALUE));
        if (!ValidationCheck.isValid(signUpInput.getGender()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(BAD_GENDER_VALUE));

        User user;
        try {
            // 2-1. 이메일 중복 체크
            String email = signUpInput.getEmail();
            User existUser = userRepository.findByEmail(email).orElse(null);
            if (existUser != null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(EXISTS_EMAIL));

            // 2-2. 닉네임 중복 체크
            String nickname = signUpInput.getNickname();
            User existNickNameUser = userRepository.findByNickname(nickname).orElse(null);
            if (existNickNameUser != null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(EXISTS_NICKNAME));

            // 3. 유저 생성
            String password = new AES128(USER_INFO_PASSWORD_KEY).encrypt(signUpInput.getPassword());
            user = User.builder()
                    .email(signUpInput.getEmail())
                    .password(password)
                    .name(signUpInput.getName())
                    .nickname(signUpInput.getNickname())
                    .birth(signUpInput.getBirth())
                    .gender(signUpInput.getGender())
                    .profile(signUpInput.getProfile())
                    .introduction(signUpInput.getIntroduction())
                    .build();

            userRepository.save(user);
            gradeRepository.save(Grade.builder().user(user).point(0).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(DATABASE_ERROR));
        }

        // 3. 토큰 생성
        String accessToken;
        try {
            accessToken = jwtService.createAccessToken(user.getId());
            if (accessToken.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(FAILED_TO_CREATE_TOKEN));
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(FAILED_TO_CREATE_TOKEN));
        }

        // 4. 결과 return
        SignUpOutput signUpOutput = new SignUpOutput(user.getId(), accessToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(signUpOutput,CREATED_USER));
    }

    @Override
    public ResponseEntity<Response<Object>> deactivate() {
        try{
            // 1. 유저 삭제
            int loginUserId = jwtService.getUserId();
            if (!ValidationCheck.isValidId(loginUserId)) {
                log.error("[users/delete] NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }
            userRepository.deleteById(loginUserId);
        } catch (Exception e){
            log.error("[users/deactivate/delete] database error" , e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(DATABASE_ERROR));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null,SUCCESS_DELETE_USER));
    }

    @Override
    public ResponseEntity<Response<ProfileOutput>> getProfile() {
        ProfileOutput profileOutput;
        try {
            User user = jwtService.getUser();
            if (user == null) {
                log.error("[users/get] NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }

            profileOutput = ProfileOutput.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickname(user.getNickname())
                    .birth(user.getBirth())
                    .gender(user.getGender())
                    .profile(user.getProfile())
                    .introduction(user.getIntroduction())
                    .build();

        } catch (Exception e){
            log.error("[users/profile] database error",e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(DATABASE_ERROR));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(profileOutput,SUCCESS_GET_PROFILE));
    }

    @Override
    public ResponseEntity<Response<Object>> updateProfile(ProfileUpdate profileUpdate) {
        try {
            User user = jwtService.getUser();
            if (user == null) {
                log.error("[users/get] NOT FOUND LOGIN USER error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response<>(NOT_FOUND_USER));
            }

            if(StringUtils.isNoneBlank(profileUpdate.getProfile()))
                user.setProfile(profileUpdate.getProfile());
            if(StringUtils.isNoneBlank(profileUpdate.getNickname())){
                User existUser = userRepository.findByNickname(profileUpdate.getNickname()).orElse(null);
                if (existUser != null)
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new Response<>(EXISTS_NICKNAME));
                user.setProfile(profileUpdate.getNickname());
            }
            if(StringUtils.isNoneBlank(profileUpdate.getIntroduction()))
                user.setProfile(profileUpdate.getIntroduction());
            if(StringUtils.isNoneBlank(profileUpdate.getPassword())){
                String newPassword = new AES128(USER_INFO_PASSWORD_KEY).encrypt(profileUpdate.getPassword());
                user.setProfile(newPassword);
            }

            userRepository.save(user);

        } catch (Exception e){
            log.error("[user/profile] database error",e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(DATABASE_ERROR));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null,SUCCESS_UPDATE_PROFILE));
    }

    @Override
    public ResponseEntity<PageResponse<UserSearchOutput>> findUserByNickname(UserSearchInput userSearchInput) {
        // 1. 값 형식 체크
        if (userSearchInput.getNickname() == null
                || !ValidationCheck.isValidPage(userSearchInput.getPage())
                || !ValidationCheck.isValidId(userSearchInput.getSize())) {
            log.info("[GET] /users?NO_VALID_STATUS");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(NO_VALUES));
        } else {
            log.info("[GET] /users?name=" + userSearchInput.getNickname());
        }
        // 2. 유저 정보 가져오기
        Page<UserSearchOutput> userSearchOutput;
        try {
            Pageable paging = PageRequest.of(userSearchInput.getPage()-1, userSearchInput.getSize(), Sort.Direction.ASC, "nickname");
            Page<User> userList;
            userList = userRepository.findByNicknameContaining(userSearchInput.getNickname(), paging);

            // 3. 유저 리스트에 필요한 최종 결과 가공
            userSearchOutput = userList.map(user -> {
                return UserSearchOutput.builder()
                        .userId(user.getId())
                        .profile(user.getProfile())
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .introduction(user.getIntroduction())
                        .build();
            });
        } catch (Exception e) {
            log.error("[users?search/get] database error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PageResponse<>(DATABASE_ERROR));
        }
        // 3. 결과 return
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageResponse<>(userSearchOutput,SUCCESS_GET_USER_LIST));
    }
}
