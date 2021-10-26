package com.cod.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "codi")
public class Codi {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "tag", columnDefinition = "TEXT")
    private String tag;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "nickname", nullable = false, length = 45)
    private String nickname;

    @Column(name = "thumbnail", nullable = false, columnDefinition = "TEXT")
    private String thumbnail;

    /**
     * 좌표 json
     */
    @Column(name = "coordinate", nullable = false, columnDefinition = "TEXT")
    private String coordinate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updated_at;
}