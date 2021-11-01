package com.cod.dao;

import com.cod.entity.Codi;
import com.cod.entity.CodiLiked;
import com.cod.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodiLikedRepository extends JpaRepository<CodiLiked, Integer> {
    int countByCodi(Codi codi);
    CodiLiked findByCodiAndUser(Codi codi,User user);
    Page<CodiLiked> findByUser(User user, Pageable paging);

}