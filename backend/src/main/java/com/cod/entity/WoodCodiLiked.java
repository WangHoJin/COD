package com.cod.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "wood_codi_liked")
public class WoodCodiLiked {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="wood_codi_id", nullable = false)
    private WoodCodi woodCodi;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}