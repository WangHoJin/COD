package com.cod.dao;

import com.cod.dto.codidiary.selectcodidiary.QSelectCodiDiaryOutput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryInput;
import com.cod.dto.codidiary.selectcodidiary.SelectCodiDiaryOutput;
import com.cod.entity.QCodiDiary;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class CodiDiaryRepositoryImpl implements CodiDiaryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    QCodiDiary qCodiDiary = QCodiDiary.codiDiary;

    @Override
    public Page<SelectCodiDiaryOutput> findByDynamicQuery(SelectCodiDiaryInput selectCodiDiaryInput, Pageable pageable) {
        QueryResults<SelectCodiDiaryOutput> queryResult = queryFactory
                .select(new QSelectCodiDiaryOutput(
                        qCodiDiary.id,
                        qCodiDiary.user.id,
                        qCodiDiary.date,
                        qCodiDiary.thumbnail,
                        qCodiDiary.content,
                        qCodiDiary.createdAt,
                        qCodiDiary.updatedAt
                ))
                .from(qCodiDiary)
                .where(eqDate(selectCodiDiaryInput.getDate()), eqUserId(selectCodiDiaryInput.getUserId()))
                .orderBy(qCodiDiary.createdAt.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetchResults();
        long totalCount = queryResult.getTotal();
        List<SelectCodiDiaryOutput> content = queryResult.getResults();

        return new PageImpl<>(content, pageable, totalCount);
    }


    private BooleanExpression eqDate(LocalDate date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        return qCodiDiary.date.eq(date);
    }

    private BooleanExpression eqUserId(Integer userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        return qCodiDiary.user.id.eq(userId);
    }

}