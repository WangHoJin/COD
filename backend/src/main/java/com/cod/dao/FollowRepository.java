package com.cod.dao;

import com.cod.entity.Follow;
import com.cod.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Page<Follow> findByFromUserId(int fromUserId, Pageable paging);
    Page<Follow> findByToUserId(int toUserId, Pageable paging);
    boolean existsByFromUserIdAndToUserId(int fromUserId, int toUserId);
    int deleteByFromUserIdAndToUserId(int fromUserId, int toUserId);
}
