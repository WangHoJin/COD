
package com.cod.dao;

import com.cod.dto.codi.selectcodi.SelectCodiInput;
import com.cod.dto.codi.selectcodi.SelectCodiOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CodiRepositoryCustom {
    Page<SelectCodiOutput> findByDynamicQuery(SelectCodiInput selectCodiInput, Pageable pageable);

}
