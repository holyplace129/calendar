package com.learn.calendar.calendar.presentation.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.learn.calendar.calendar.presentation.dto.response.QScheduleResponse is a Querydsl Projection type for ScheduleResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QScheduleResponse extends ConstructorExpression<ScheduleResponse> {

    private static final long serialVersionUID = 1187919865L;

    public QScheduleResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate, com.querydsl.core.types.Expression<String> deviceToken) {
        super(ScheduleResponse.class, new Class<?>[]{long.class, String.class, java.time.LocalDate.class, java.time.LocalDate.class, String.class}, id, title, startDate, endDate, deviceToken);
    }

}

