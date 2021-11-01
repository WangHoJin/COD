package com.cod.dao;

import com.cod.dto.wood.selectwood.QSelectWoodListOutput;
import com.cod.dto.wood.selectwood.SelectWoodInput;
import com.cod.dto.wood.selectwood.SelectWoodListOutput;
import com.cod.entity.QCodi;
import com.cod.entity.QWood;
import com.cod.entity.QWoodCodi;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
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
    QWoodCodi qWoodCodi = QWoodCodi.woodCodi;

    @Override
    public Page<SelectWoodListOutput> findByDynamicQuery(SelectWoodInput selectWoodInput, Pageable pageable) {
        QueryResults<SelectWoodListOutput> queryResult = queryFactory
                .select(new QSelectWoodListOutput(
                        qWoodCodi.id,
                        qWood.id,
                        qWood.user.id,
                        qWood.title,
                        qWood.content,
                        qWood.terminatedAt,
                        // woodCodiCnt
                        JPAExpressions.select(qWoodCodi.count().castToNum(Integer.class)).from(qWoodCodi)
                                .where(qWoodCodi.wood.id.eq(qWood.id))
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

