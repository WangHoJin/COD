
package com.cod.dao;

import com.cod.dto.wood.selectwood.SelectWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WoodRepositoryCustom {
    Page<SelectWoodOutput> findByDynamicQuery(SelectWoodInput selectWoodInput, Pageable pageable);
}
