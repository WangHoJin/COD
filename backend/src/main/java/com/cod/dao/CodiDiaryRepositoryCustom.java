
package com.cod.dao;

import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface CodiDiaryRepositoryCustom {
    Page<SelectCodiDiaryOutput> findByDynamicQuery(SelectCodiDiaryInput selectCodiDiaryInput, Pageable pageable);
}
