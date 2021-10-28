package com.cod.dao;

import com.cod.entity.Wood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoodRepository extends JpaRepository<Wood, Integer>{
}