package com.cod.dto.notice.createnotice;

import com.cod.dto.notice.NoticeType;
import com.cod.entity.Codi;
import com.cod.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateNoticeInput {
    /**
     *  1 : 팔로워
     *  2 : 좋아요
     *  3 : 댓글
     */
    private String type;
    private String message;
    private int receiveUserId;
    private Integer sendUserId;
    /**
     * 좋아요나 댓글이 달린 코디
     */
    private Integer codiId;
}
