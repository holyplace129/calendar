package com.learn.calendar.calendar.presentation.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.learn.calendar.calendar.presentation.dto.response.QScheduleDayResponse is a Querydsl Projection type for ScheduleDayResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QScheduleDayResponse extends ConstructorExpression<ScheduleDayResponse> {

    private static final long serialVersionUID = 114901317L;

    public QScheduleDayResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate, com.querydsl.core.types.Expression<java.time.LocalTime> startTime, com.querydsl.core.types.Expression<java.time.LocalTime> endTime, com.querydsl.core.types.Expression<String> deviceToken) {
        super(ScheduleDayResponse.class, new Class<?>[]{long.class, String.class, java.time.LocalDate.class, java.time.LocalDate.class, java.time.LocalTime.class, java.time.LocalTime.class, String.class}, id, title, startDate, endDate, startTime, endTime, deviceToken);
    }

}

