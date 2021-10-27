
package com.cod.dao;

import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import com.cod.entity.Codi;
import com.cod.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CodiRepositoryCustom {
    Page<SelectCodiOutput> findByDynamicQuery(SelectCodiInput selectCodiInput, Pageable pageable);
    Page<SelectCodiOutput> getPopularCodi(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);
    Page<SelectCodiOutput> getFollowingUserCodi(int userId, Pageable pageable);
}
