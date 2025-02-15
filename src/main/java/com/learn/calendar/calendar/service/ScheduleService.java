package com.learn.calendar.calendar.service;

import com.learn.calendar.calendar.domain.Schedule;
import com.learn.calendar.calendar.domain.ScheduleDetail;
import com.learn.calendar.calendar.domain.repository.ScheduleDetailRepository;
import com.learn.calendar.calendar.domain.repository.ScheduleRepository;
import com.learn.calendar.calendar.presentation.dto.request.CreateScheduleRequest;
import com.learn.calendar.calendar.presentation.dto.request.UpdateScheduleRequest;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleDayResponse;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleDetailResponse;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDetailRepository scheduleDetailRepository;

    // 일정 생성
    public ScheduleDetailResponse createSchedule(CreateScheduleRequest createScheduleRequest) {
        // 일정 생성
        Schedule schedule = Schedule.builder()
                .title(createScheduleRequest.getTitle())
                .isAllDay(createScheduleRequest.getIsAllDay())
                .deviceToken(createScheduleRequest.getDeviceToken())
                .build();

        scheduleRepository.save(schedule);

        // 일정 디테일 생성
        ScheduleDetail scheduleDetail = ScheduleDetail.builder()
                .startDate(createScheduleRequest.getStartDate())
                .endDate(createScheduleRequest.getEndDate())
                .startTime(createScheduleRequest.getStartTime())
                .endTime(createScheduleRequest.getEndTime())
                .description(createScheduleRequest.getDescription())
                .isAlarmEnabled(createScheduleRequest.getIsAlarmEnabled())
                .schedule(schedule)
                .build();

        scheduleDetailRepository.save(scheduleDetail);

        schedule.getScheduleDetails().add(scheduleDetail);
        scheduleRepository.save(schedule);

        return ScheduleDetailResponse.from(schedule, scheduleDetail);
    }

    // 월 별 일정 조회
    public List<ScheduleResponse> findScheduleByMonth(int year, int month) {
        return scheduleDetailRepository.findSchedulesByMonth(year, month);
    }

    // 일 별 일정 조회
    public List<ScheduleDayResponse> findScheduleByDay(LocalDate date) {
        return scheduleDetailRepository.findScheduleByDay(date);
    }

    // 일정 상세 조회
    public ScheduleDetailResponse getScheduleDetail(Long scheduleId) {
        return scheduleDetailRepository.findScheduleDetailById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));
    }

    // 일정 수정
    @Transactional
    public ScheduleDetailResponse updateScheduleDetail(Long scheduleId, UpdateScheduleRequest updateScheduleRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        ScheduleDetail scheduleDetail = scheduleDetailRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정 상세정보를 찾을 수 없습니다."));

        if (updateScheduleRequest.getTitle() != null) {
            schedule.updateTitle(updateScheduleRequest.getTitle());
        }

        if (updateScheduleRequest.getIsAllDay() != null) {
            schedule.updateIsAllDay(updateScheduleRequest.getIsAllDay());
        }

        scheduleDetail.updateScheduleDetail(
                updateScheduleRequest.getStartDate() != null ? updateScheduleRequest.getStartDate() : scheduleDetail.getStartDate(),
                updateScheduleRequest.getEndDate() != null ? updateScheduleRequest.getEndDate() : scheduleDetail.getEndDate(),
                updateScheduleRequest.getStartTime() != null ? updateScheduleRequest.getStartTime() : scheduleDetail.getStartTime(),
                updateScheduleRequest.getEndTime() != null ? updateScheduleRequest.getEndTime() : scheduleDetail.getEndTime(),
                updateScheduleRequest.getDescription() != null ? updateScheduleRequest.getDescription() : scheduleDetail.getDescription(),
                updateScheduleRequest.getIsAlarmEnabled()
        );

        return ScheduleDetailResponse.from(schedule, scheduleDetail);
    }

    // 일정 삭제
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        ScheduleDetail scheduleDetail = scheduleDetailRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        scheduleDetailRepository.delete(scheduleDetail);
        scheduleRepository.delete(schedule);
    }
}
