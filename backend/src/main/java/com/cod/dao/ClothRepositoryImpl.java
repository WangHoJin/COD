package com.cod.dao;

import com.cod.dto.cloth.selectcloth.QSelectClothOutput;
import com.cod.dto.cloth.selectcloth.SelectClothInput;
import com.cod.dto.cloth.selectcloth.SelectClothOutput;
import com.cod.entity.QCloth;
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

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ClothRepositoryImpl implements ClothRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    QCloth qCloth = QCloth.cloth;

    @Override
    public Page<SelectClothOutput> findByDynamicQuery(SelectClothInput selectClothInput, Pageable pageable) {
        QueryResults<SelectClothOutput> queryResult = queryFactory
                .select(new QSelectClothOutput(
                        qCloth.id,
                        qCloth.user.id,
                        qCloth.name,
                        qCloth.tag,
                        qCloth.imgUrl,
                        qCloth.type,
                        qCloth.color,
                        qCloth.season,
                        qCloth.isOwned,
                        qCloth.brand,
                        qCloth.price,
                        qCloth.measure,
                        qCloth.createdAt,
                        qCloth.updatedAt
                ))
                .from(qCloth)
                .where(eqUserId(selectClothInput.getUserId()),
                        eqType(selectClothInput.getType()), eqSeason(selectClothInput.getSeason()))
                .orderBy(qCloth.createdAt.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetchResults();
        long totalCount = queryResult.getTotal();
        List<SelectClothOutput> content = queryResult.getResults();

        return new PageImpl<>(content, pageable, totalCount);
    }
//    @Override
//    public Page<SelectClothOutput> findByDynamicQuery(SelectClothInput selectClothInput, Pageable pageable) {
//        return null;
//    }

    private BooleanExpression eqSeason(String season) {
        if (StringUtils.isEmpty(season)) {
            return null;
        }
        return qCloth.season.eq(season);
    }

    private BooleanExpression eqType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        return qCloth.type.eq(type);
    }

    private BooleanExpression eqUserId(Integer userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        return qCloth.user.id.eq(userId);
    }

}

