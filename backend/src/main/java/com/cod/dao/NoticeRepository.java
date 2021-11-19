package com.cod.dao;

import com.cod.dto.notice.selectnotice.SelectNoticeOutput;
import com.cod.entity.Follow;
import com.cod.entity.Notice;
import com.cod.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Page<Notice> findByReceiveUser(User receiveUser, Pageable pageable);
    @Query(value="select count(*) from notice n where n.receive_user_id=?1 and n.is_checked=false", nativeQuery = true)
    int countNewNotice(int receiveUserId);
}