package com.cod.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.*;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "nickname", nullable = false, length = 45)
    private String nickname;

    @Column(name = "birth", nullable = false)
    private Date birth;

    @Column(name = "gender", nullable = false, length = 5)
    private String gender;

    /**
     * 프로필 사진
     */
    @Column(name = "profile", columnDefinition = "TEXT")
    private String profile;

    /**
     * 소개글
     */
    @Column(name = "introduction", length = 45)
    private String introduction;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updated_at;
}