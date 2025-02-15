package com.learn.calendar.calendar.domain.repository;

import com.learn.calendar.calendar.domain.ScheduleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail, Long>, ScheduleDetailRepositoryCustom {

    Optional<ScheduleDetail> findByScheduleId(Long scheduleId);

    @Query("SELECT d FROM ScheduleDetail d JOIN FETCH d.schedule")
    List<ScheduleDetail> findAllWithSchedule();
}
