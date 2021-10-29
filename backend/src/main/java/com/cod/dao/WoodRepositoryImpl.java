package com.cod.dao;

import com.cod.dto.wood.selectwood.QSelectWoodListOutput;
import com.cod.dto.wood.selectwood.SelectWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodListOutput;
import com.cod.entity.QWood;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class WoodRepositoryImpl implements WoodRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    QWood qWood = QWood.wood;

    @Override
    public Page<SelectWoodListOutput> findByDynamicQuery(SelectWoodInput selectWoodInput, Pageable pageable) {
        QueryResults<SelectWoodListOutput> queryResult = queryFactory
                .select(new QSelectWoodListOutput(
                        qWood.user.id,
                        qWood.title,
                        qWood.content,
                        qWood.terminatedAt
                ))
                .from(qWood)
                .where(eqUserId(selectWoodInput.getUserId()))
                .orderBy(qWood.createdAt.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetchResults();

        long totalCount = queryResult.getTotal();
        List<SelectWoodListOutput> list = queryResult.getResults();

        return new PageImpl<>(list, pageable, totalCount);
    }


    private BooleanExpression eqUserId(Integer userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        return qWood.user.id.eq(userId);
    }

}

