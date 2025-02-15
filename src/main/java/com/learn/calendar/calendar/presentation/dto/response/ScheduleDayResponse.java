package com.learn.calendar.calendar.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ScheduleDayResponse {

    private final Long id;
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String deviceToken;

    @QueryProjection
    public ScheduleDayResponse(Long id, String title, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String deviceToken) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deviceToken = deviceToken;
    }
}
