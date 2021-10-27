package com.cod.dao;

import com.cod.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);
    List<User> findByNickname(String nickname);
    Page<User> findByNicknameContaining(String nickname, Pageable paging);
    Page<User> findByIntroductionContaining(String content, Pageable paging);
}