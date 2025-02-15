package com.learn.calendar.calendar.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Table(name = "schedule_detail")
@RequiredArgsConstructor
public class ScheduleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    private String description;

    @ColumnDefault("true")
    private Boolean isAlarmEnabled;

    @Builder
    public ScheduleDetail(Schedule schedule, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String description, Boolean isAlarmEnabled) {
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isAlarmEnabled = isAlarmEnabled != null ? isAlarmEnabled : false;
    }

    public void  updateAlarmEnabled(Boolean isAlarmEnable) {
        this.isAlarmEnabled = isAlarmEnable;
    }

    public void updateScheduleDetail(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String description, Boolean isAlarmEnabled) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isAlarmEnabled = isAlarmEnabled != null ? isAlarmEnabled : false;
    }
}
