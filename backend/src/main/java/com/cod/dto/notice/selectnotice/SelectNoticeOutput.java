package com.cod.dto.notice.selectnotice;

import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.dto.user.profile.ProfileOutput;
import com.cod.entity.Codi;
import com.cod.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SelectNoticeOutput {
    /**
     *  1 : 팔로워
     *  2 : 좋아요
     *  3 : 댓글
     */
    private int id;
    private String type;
    private String message;
    private ProfileOutput sendUser;
    private boolean isChecked;
    private LocalDateTime created_at;
    /**
     * 좋아요나 댓글이 달린 코디
     */
    private SelectCodiOutput codi;
}
