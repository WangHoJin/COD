package com.cod.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CodiRepositoryImpl implements CodiRepositoryCustom {
    private final JPAQueryFactory queryFactory;

}

