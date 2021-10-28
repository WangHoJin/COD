package com.cod.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "wood_codi")
public class WoodCodi {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="wood_id", nullable = false)
    private Wood wood;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "tag", columnDefinition = "TEXT")
    private String tag;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail", nullable = false, columnDefinition = "TEXT")
    private String thumbnail;

    /**
     * 좌표 json
     */
    @Column(name = "coordinate", nullable = false, columnDefinition = "TEXT")
    private String coordinate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;
}