package com.learn.calendar.calendar.presentation.dto.response;

import com.learn.calendar.calendar.domain.Schedule;
import com.learn.calendar.calendar.domain.ScheduleDetail;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ScheduleDetailResponse {

    private final Long id;
    private final String title;
    private final Boolean isAllDay;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String description;
    private final Boolean isAlarmEnabled;

    public static ScheduleDetailResponse from(Schedule schedule, ScheduleDetail scheduleDetail) {
        return new ScheduleDetailResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getIsAllDay(),
                scheduleDetail.getStartDate(),
                scheduleDetail.getEndDate(),
                scheduleDetail.getStartTime(),
                scheduleDetail.getEndTime(),
                scheduleDetail.getDescription(),
                scheduleDetail.getIsAlarmEnabled()
        );
    }

    @QueryProjection
    public ScheduleDetailResponse(Long id, String title, Boolean isAllDay, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String description, Boolean isAlarmEnabled) {
        this.id = id;
        this.title = title;
        this.isAllDay = isAllDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isAlarmEnabled = isAlarmEnabled;
    }
}
