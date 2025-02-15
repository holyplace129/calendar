package com.learn.calendar.calendar.presentation.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.learn.calendar.calendar.presentation.dto.response.QScheduleDetailResponse is a Querydsl Projection type for ScheduleDetailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QScheduleDetailResponse extends ConstructorExpression<ScheduleDetailResponse> {

    private static final long serialVersionUID = -680122006L;

    public QScheduleDetailResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<Boolean> isAllDay, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate, com.querydsl.core.types.Expression<java.time.LocalTime> startTime, com.querydsl.core.types.Expression<java.time.LocalTime> endTime, com.querydsl.core.types.Expression<String> description, com.querydsl.core.types.Expression<Boolean> isAlarmEnabled) {
        super(ScheduleDetailResponse.class, new Class<?>[]{long.class, String.class, boolean.class, java.time.LocalDate.class, java.time.LocalDate.class, java.time.LocalTime.class, java.time.LocalTime.class, String.class, boolean.class}, id, title, isAllDay, startDate, endDate, startTime, endTime, description, isAlarmEnabled);
    }

}

