package com.learn.calendar.calendar.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateScheduleRequest {

    private String title;
    private Boolean isAllDay;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private Boolean isAlarmEnabled;

}
