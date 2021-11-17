package com.cod.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "cloth")
public class Cloth {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "img_url", nullable = false, columnDefinition = "TEXT")
    private String imgUrl;

    @ManyToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "season", nullable = false, length = 45)
    private String season;

    @Column(name = "is_owned", nullable = false)
    private Boolean isOwned;

    @Column(name = "tag", columnDefinition = "TEXT")
    private String tag;

    @Column(name = "price")
    private int price;

    @Column(name = "brand",  length = 45)
    private String brand;

    @Column(name = "name",  length = 45)
    private String name;

    @Column(name = "measure",  length = 20)
    private String measure;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
