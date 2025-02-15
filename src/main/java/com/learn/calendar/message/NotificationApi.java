package com.learn.calendar.message;

import com.learn.calendar.calendar.domain.repository.ScheduleDetailRepository;
import com.learn.calendar.calendar.domain.repository.ScheduleRepository;
import com.learn.calendar.calendar.presentation.dto.response.ScheduleDayResponse;
import com.learn.calendar.common.handler.CustomWebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/noty")
@RequiredArgsConstructor
@Log4j2
public class NotificationApi {

    private final ScheduleDetailRepository scheduleDetailRepository;
    private final ScheduleRepository scheduleRepository;
    private final CustomWebSocketHandler customWebSocketHandler;

    @GetMapping("")
    public ResponseEntity<String> sendScheduleAlarms() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        log.info("Current date: {}", date);
        log.info("Current time: {}", time);

        // 알림이 필요한 일정을 조회
        List<ScheduleDayResponse> scheduleResponses = scheduleDetailRepository.findSchedulesForAlarms(date, time);
        log.info("Found schedules: {}", scheduleResponses);


        // 일정에 대해 알림을 전송
        for (ScheduleDayResponse scheduleResponse : scheduleResponses) {
            // deviceToken을 바로 가져옴
            String deviceToken = scheduleResponse.getDeviceToken();
            WebSocketSession session = customWebSocketHandler.getSessionByDeviceToken(deviceToken);
            log.info("Session for deviceToken {}: {}", deviceToken, session);


            // 세션이 존재하고 열린 상태일 경우 메시지 전송
            if (session != null && session.isOpen()) {
                try {
                    // 알림 메시지 전송
                    session.sendMessage(new TextMessage("일정 알림 : " + scheduleResponse.getTitle()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseEntity.ok("success");
    }
}
