package com.learn.calendar.calendar.domain.repository;

import com.learn.calendar.calendar.presentation.dto.response.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.learn.calendar.calendar.domain.QSchedule.schedule;
import static com.learn.calendar.calendar.domain.QScheduleDetail.scheduleDetail;


@Repository
public class ScheduleDetailRepositoryImpl implements ScheduleDetailRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ScheduleDetailRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ScheduleResponse> findSchedulesByMonth(int year, int month) {
        LocalDate startOfMonth = YearMonth.of(year, month).atDay(1);
        LocalDate endOfMonth = YearMonth.of(year, month).atEndOfMonth();

        return queryFactory
                .select(new QScheduleResponse(
                        schedule.id,
                        schedule.title,
                        scheduleDetail.startDate,
                        scheduleDetail.endDate,
                        schedule.deviceToken
                ))
                .from(scheduleDetail)
                .join(scheduleDetail.schedule, schedule)
                .where(
                        scheduleDetail.startDate.loe(endOfMonth)
                                .and(scheduleDetail.endDate.goe(startOfMonth))
                )
                .fetch();
    }

    // 일 별 일정 조회
    @Override
    public List<ScheduleDayResponse> findScheduleByDay(LocalDate date) {
        return queryFactory
                .select(new QScheduleDayResponse(
                        schedule.id,
                        schedule.title,
                        scheduleDetail.startDate, // 필요한 다른 필드들도 추가
                        scheduleDetail.endDate,
                        scheduleDetail.startTime,
                        scheduleDetail.endTime,
                        schedule.deviceToken
                ))
                .from(scheduleDetail)
                .join(scheduleDetail.schedule, schedule)
                .where(
                        scheduleDetail.startDate.loe(date)
                                .and(scheduleDetail.endDate.goe(date))
                )
                .fetch();
    }

    // 일정 상세 조회
    @Override
    public Optional<ScheduleDetailResponse> findScheduleDetailById(Long scheduleId) {
        return Optional.ofNullable(
                queryFactory
                        .select(new QScheduleDetailResponse(
                                schedule.id,
                                schedule.title,
                                schedule.isAllDay,
                                scheduleDetail.startDate,
                                scheduleDetail.endDate,
                                scheduleDetail.startTime,
                                scheduleDetail.endTime,
                                scheduleDetail.description,
                                scheduleDetail.isAlarmEnabled
                        ))
                        .from(scheduleDetail)
                        .join(scheduleDetail.schedule, schedule)
                        .where(schedule.id.eq(scheduleId))
                        .fetchOne()
        );
    }

    @Override
    public List<ScheduleDayResponse> findSchedulesForAlarms(LocalDate date, LocalTime time) {
        return queryFactory
                .select(new QScheduleDayResponse(
                        schedule.id,
                        schedule.title,
                        scheduleDetail.startDate, // 필요한 다른 필드들도 추가
                        scheduleDetail.endDate,
                        scheduleDetail.startTime,
                        scheduleDetail.endTime,
                        schedule.deviceToken
                        ))
                .from(scheduleDetail)
                .join(scheduleDetail.schedule, schedule)
                .where(
                        scheduleDetail.isAlarmEnabled.isTrue()
                                .and(scheduleDetail.startDate.eq(date))
                                .and(schedule.isAllDay.isTrue())
                                .and(scheduleDetail.startTime.isNull())

                                .or(
                                        scheduleDetail.isAlarmEnabled.isTrue()
                                                .and(scheduleDetail.startDate.eq(date))
//                                                .and(scheduleDetail.startTime.eq(time))
                                                .and(Expressions.stringTemplate("FORMATDATETIME({0}, 'HH:mm')", scheduleDetail.startTime).eq(time.format(DateTimeFormatter.ofPattern("HH:mm")))) // 시, 분 비교
                                                .and(schedule.isAllDay.isFalse())
                                )
                )
                .fetch();
    }

}
