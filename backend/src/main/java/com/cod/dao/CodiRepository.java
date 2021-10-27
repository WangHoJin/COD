package com.cod.dao;

import com.cod.entity.Codi;
import com.cod.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodiRepository extends JpaRepository<Codi, Integer>, CodiRepositoryCustom {

}