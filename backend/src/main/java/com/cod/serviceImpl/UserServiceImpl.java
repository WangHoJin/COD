package com.cod.serviceImpl;

import com.cod.configuration.ValidationCheck;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.dto.user.profile.ProfileUpdate;
import com.cod.dto.user.search.UserSearchInput;
import com.cod.dto.user.search.UserSearchOutput;
import com.cod.dto.user.signin.SignInInput;
import com.cod.dto.user.signup.SignUpInput;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service("UserService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public Response<SignInOutput> signIn(SignInInput signInInput) {
        // 1. 값 형식 체크
        if (signInInput == null) return new Response<>(ResponseStatus.NO_VALUES);
        if (!ValidationCheck.isValid(signInInput.getEmail()))    return new Response<>(ResponseStatus.BAD_EMAIL_VALUE);
        if (!ValidationCheck.isValid(signInInput.getPassword())) return new Response<>(ResponseStatus.BAD_PASSWORD_VALUE);

        // 2. user 정보 가져오기
        User user;
        try {
            String email = signInInput.getEmail();
            String password = signInInput.getPassword();
            user = userRepository.findByEmail(email).orElse(null);
            if (user==null) {
                return new Response<>(ResponseStatus.NOT_FOUND_USER);
            } else if (!user.getPassword().equals(password)) {
                return new Response<>(ResponseStatus.FAILED_TO_SIGN_IN);
            }
        } catch (Exception e) {
            return new Response<>(ResponseStatus.DATABASE_ERROR);
        }

        // 3. access token 생성
        String accessToken;
        try{
            accessToken = jwtService.createAccessToken(user.getId());
            if (accessToken.isEmpty()) {
                return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
            }
        } catch (Exception e) {
            return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
        }

        // 4. 결과 return
        SignInOutput signInOutput = new SignInOutput(user.getId(), accessToken);
        return new Response<>(signInOutput, ResponseStatus.SUCCESS_SIGN_IN);
    }

    @Override
    public Response<SignUpOutput> signUp(SignUpInput signUpInput) {
        // 1. 값 형식 체크
        if (signUpInput == null) return new Response<>(ResponseStatus.NO_VALUES);
        if (!ValidationCheck.isValid(signUpInput.getEmail()))    return new Response<>(ResponseStatus.BAD_EMAIL_VALUE);
        if (!ValidationCheck.isValid(signUpInput.getPassword())) return new Response<>(ResponseStatus.BAD_PASSWORD_VALUE);
        if (!ValidationCheck.isValid(signUpInput.getName())) return new Response<>(ResponseStatus.BAD_NAME_VALUE);
        if (!ValidationCheck.isValid(signUpInput.getNickname()))     return new Response<>(ResponseStatus.BAD_NICKNAME_VALUE);
        if (!ValidationCheck.isValidDate(Date.valueOf(signUpInput.getBirth()))) return new Response<>(ResponseStatus.BAD_BIRTH_VALUE);
        if (!ValidationCheck.isValid(signUpInput.getGender())) return new Response<>(ResponseStatus.BAD_GENDER_VALUE);

        // 2. 유저 생성
        User user = User.builder()
                .email(signUpInput.getEmail())
                .password(signUpInput.getPassword())
                .name(signUpInput.getName())
                .nickname(signUpInput.getNickname())
                .birth(signUpInput.getBirth())
                .gender(signUpInput.getGender())
                .profile(signUpInput.getProfile())
                .introduction(signUpInput.getIntroduction())
                .build();

        try {
            String email = signUpInput.getEmail();
            User existUser = userRepository.findByEmail(email).orElse(null);
            if (existUser != null) {
                return new Response<>(ResponseStatus.EXISTS_EMAIL);
            } else {
                userRepository.save(user);
            }
        } catch (Exception e) {
            return new Response<>(ResponseStatus.DATABASE_ERROR);
        }

        // 3. 토큰 생성
        String accessToken;
        try {
            accessToken = jwtService.createAccessToken(user.getId());
            if (accessToken.isEmpty()) {
                return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
            }
        } catch (Exception exception) {
            return new Response<>(ResponseStatus.FAILED_TO_CREATE_TOKEN);
        }

        // 4. 결과 return
        SignUpOutput signUpOutput = new SignUpOutput(user.getId(), accessToken);
        return new Response<>(signUpOutput, ResponseStatus.CREATED_USER);
    }

    @Override
    public Response<Object> deactivate() {
        try{
            // 1. 유저 삭제
            int loginUserId = jwtService.getUserId();
            if (loginUserId <= 0) {
                log.error("[users/delete] NOT FOUND LOGIN USER error");
                return new Response<>(ResponseStatus.NOT_FOUND_USER);
            }
            userRepository.deleteById(loginUserId);
        } catch (Exception e){
            log.error("[users/deactivate/delete] database error" , e);
            return new Response<>(ResponseStatus.DATABASE_ERROR);
        }
        return new Response<>(null,ResponseStatus.SUCCESS_DELETE_USER);
    }

    @Override
    public Response<ProfileOutput> getProfile() {
        ProfileOutput profileOutput;
        try {
            User user = jwtService.getUser();
            if (user == null) {
                log.error("[users/get] NOT FOUND LOGIN USER error");
                return new Response<>(ResponseStatus.NOT_FOUND_USER);
            }

            profileOutput = ProfileOutput.builder()
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
            return new Response<>(ResponseStatus.DATABASE_ERROR);
        }

        return new Response<>(profileOutput, ResponseStatus.SUCCESS_GET_PROFILE);
    }

    @Override
    public Response<Object> updateProfile(ProfileUpdate profileUpdate) {
        try {
            User user = jwtService.getUser();
            if (user == null) {
                log.error("[users/get] NOT FOUND LOGIN USER error");
                return new Response<>(ResponseStatus.NOT_FOUND_USER);
            }

            String email = user.getEmail();
            String password = user.getPassword();
            String name= user.getName();
            LocalDate birth=user.getBirth();
            String gender=user.getGender();
            String nickname = profileUpdate.getNickname() == null ? user.getNickname() : profileUpdate.getNickname();
            String profile = profileUpdate.getProfile() == null ? user.getProfile() : profileUpdate.getProfile();
            String introduction = profileUpdate.getIntroduction() == null ? user.getIntroduction() : profileUpdate.getIntroduction();

            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setNickname(nickname);
            user.setBirth(birth);
            user.setGender(gender);
            user.setProfile(profile);
            user.setIntroduction(introduction);

            if(StringUtils.isNoneBlank(profileUpdate.getNickname())){
                User existUser = userRepository.findByNickname(nickname).orElse(null);
                if (existUser != null)
                    return new Response<>(ResponseStatus.EXISTS_NICKNAME);
            }

            userRepository.save(user);

        } catch (Exception e){
            log.error("[user/profile] database error",e);
            return new Response<>(ResponseStatus.DATABASE_ERROR);
        }

        return new Response<>(null, ResponseStatus.SUCCESS_UPDATE_PROFILE);
    }

    @Override
    public PageResponse<UserSearchOutput> getUser(UserSearchInput userSearchInput) {
        // 1. 값 형식 체크
        if (userSearchInput.getNickname() == null
                || !ValidationCheck.isValidPage(userSearchInput.getPage())
                || !ValidationCheck.isValidId(userSearchInput.getSize())) {
            log.info("[GET] /users?NO_VALID_STATUS");
            return new PageResponse<>(ResponseStatus.NO_VALUES);
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
                        .nickname(user.getNickname())
                        .introduction(user.getIntroduction())
                        .build();
            });
        } catch (Exception e) {
            log.error("[users?search/get] database error", e);
            return new PageResponse<>(ResponseStatus.DATABASE_ERROR);
        }
        // 3. 결과 return
        return new PageResponse<>(userSearchOutput, ResponseStatus.SUCCESS_GET_USER_LIST);
    }
}
