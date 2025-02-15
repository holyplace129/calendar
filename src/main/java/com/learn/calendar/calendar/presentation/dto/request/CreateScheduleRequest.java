package com.learn.calendar.calendar.presentation.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class CreateScheduleRequest {

    private String title;
    private Boolean isAllDay;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private Boolean isAlarmEnabled;
    private String deviceToken;
}
