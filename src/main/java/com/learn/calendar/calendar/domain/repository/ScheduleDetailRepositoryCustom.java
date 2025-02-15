package com.learn.calendar.calendar.domain.repository;

import com.learn.calendar.calendar.presentation.dto.response.ScheduleDayResponse;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleDetailResponse;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleDetailRepositoryCustom {

    // 월 별 일정 조회
    List<ScheduleResponse> findSchedulesByMonth(int year, int month);

    // 일 별 일정 조회
    List<ScheduleDayResponse> findScheduleByDay(LocalDate date);

    // 단일 일정 상세 조회
    Optional<ScheduleDetailResponse> findScheduleDetailById(Long scheduleId);

    // 날짜에 맞는 스케줄 조회
    List<ScheduleDayResponse> findSchedulesForAlarms(LocalDate date, LocalTime time);
}
