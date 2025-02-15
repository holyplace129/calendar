package com.learn.calendar.wish.service.util;


import com.learn.calendar.wish.domain.Frequency;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    // 마감일 계산 로직
    public static LocalDate calculateEndDate(LocalDate startAt, long totalDays, Frequency frequency) {
        return switch (frequency) {
            case DAILY -> startAt.plusDays(totalDays);
            case WEEKLY -> startAt.plusWeeks(totalDays);
            case MONTHLY -> startAt.plusMonths(totalDays);
            default -> throw new IllegalArgumentException("지원하지 않는 주기입니다.");
        };
    }

    // 남은 일수 계산
    public static long calculateTotalDays(Long currentAmount, Long dayDeposit, Frequency frequency) {
        return currentAmount / dayDeposit;
    }

}
