package com.cod.dao;

import com.cod.entity.Wood;
import com.cod.entity.WoodCodi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoodCodiRepository extends JpaRepository<WoodCodi, Integer> {
    Page<WoodCodi> findByWood(Wood wood, Pageable pageable);
}