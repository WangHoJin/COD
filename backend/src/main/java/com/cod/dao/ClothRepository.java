package com.cod.dao;

import com.cod.entity.Cloth;
import com.cod.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Integer> {
    Page<Cloth> findByUser(User user, Pageable paging);
    Page<Cloth> findByUserAndType(User user,String type, Pageable paging);
}