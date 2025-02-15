package com.learn.calendar.calendar.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponse {

    private final Long id;
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String deviceToken;

    @QueryProjection
    public ScheduleResponse(Long id, String title, LocalDate startDate, LocalDate endDate, String deviceToken) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deviceToken = deviceToken;
    }
}
