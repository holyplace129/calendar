package com.learn.calendar.calendar.presentation;

import com.learn.calendar.calendar.presentation.dto.request.CreateScheduleRequest;
import com.learn.calendar.calendar.presentation.dto.request.UpdateScheduleRequest;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleDayResponse;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleDetailResponse;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleResponse;
import com.learn.calendar.calendar.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleApi {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("")
    public ResponseEntity<ScheduleDetailResponse> createSchedule(@RequestBody CreateScheduleRequest createScheduleRequest) {
        ScheduleDetailResponse response = scheduleService.createSchedule(createScheduleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 월 별 일정 조회
    @GetMapping("/month")
    public ResponseEntity<List<ScheduleResponse>> findScheduleByMonth(@RequestParam int year,
                                                                      @RequestParam int month) {
        List<ScheduleResponse> responses = scheduleService.findScheduleByMonth(year, month);
        return ResponseEntity.ok(responses);
    }

    // 일 별 일정 조회
    @GetMapping("/day")
    public ResponseEntity<List<ScheduleDayResponse>> findScheduleByDay(@RequestParam LocalDate date) {
        List<ScheduleDayResponse> responses = scheduleService.findScheduleByDay(date);
        return ResponseEntity.ok(responses);
    }

    // 일정 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDetailResponse> findScheduleDetail(@PathVariable Long id) {
        ScheduleDetailResponse response = scheduleService .getScheduleDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleDetailResponse> updateScheduleDetail(@PathVariable Long id,
                                                                       @RequestBody UpdateScheduleRequest updateScheduleRequest) {
        ScheduleDetailResponse response = scheduleService.updateScheduleDetail(id, updateScheduleRequest);
        return ResponseEntity.ok(response);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
