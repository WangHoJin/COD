
package com.cod.dao;

import com.cod.dto.cloth.selectcloth.SelectClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ClothRepositoryCustom {
    Page<SelectClothOutput> findByDynamicQuery(SelectClothInput selectClothInput, Pageable pageable);
}
