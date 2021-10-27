package com.cod.dao;

import com.cod.entity.Codi;
import com.cod.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CodiRepository extends JpaRepository<Codi, Integer>, CodiRepositoryCustom {
}