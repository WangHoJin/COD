package com.cod.dao;

import com.cod.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoodCodiLikedRepository extends JpaRepository<WoodCodiLiked, Integer> {
    int countByWoodCodi(WoodCodi woodCodi);
    WoodCodiLiked findByWoodCodiAndUser(WoodCodi woodCodi, User user);
}