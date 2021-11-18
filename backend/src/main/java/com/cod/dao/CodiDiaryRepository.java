package com.cod.dao;

import com.cod.entity.CodiDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodiDiaryRepository extends JpaRepository<CodiDiary, Integer>, CodiDiaryRepositoryCustom {
}