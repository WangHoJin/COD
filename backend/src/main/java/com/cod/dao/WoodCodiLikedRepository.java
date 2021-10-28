package com.cod.dao;

import com.cod.entity.Codi;
import com.cod.entity.CodiLiked;
import com.cod.entity.WoodCodi;
import com.cod.entity.WoodCodiLiked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoodCodiLikedRepository extends JpaRepository<WoodCodiLiked, Integer> {
    int countByWoodCodi(WoodCodi woodCodi);
}