package com.cod.entity;

import com.cod.dto.notice.NoticeType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "notice")
public class Notice {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    /**
     *  1 : 팔로워
     *  2 : 좋아요
     *  3 : 댓글
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    NoticeType type;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="receive_user_id", nullable = false)
    private User receiveUser;

    @ManyToOne
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name="send_user_id")
    private User sendUser;

    @Column(name = "is_checked", nullable = false)
    private boolean isChecked;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 좋아요나 댓글이 달린 코디
     */
    @ManyToOne
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name="codi_id")
    private Codi codi;

}
