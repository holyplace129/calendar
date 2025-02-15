package com.learn.calendar.calendar.domain;

import com.learn.calendar.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "schedule")
@RequiredArgsConstructor
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ColumnDefault("true")
    private Boolean isAllDay;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.PERSIST) // CascadeType.PERSIST 추가
    private List<ScheduleDetail> scheduleDetails = new ArrayList<>();

    private String deviceToken;

    @Builder
    public Schedule(String title, Boolean isAllDay, String deviceToken) {
        this.title = title;
        this.isAllDay = isAllDay;
        this.deviceToken = deviceToken;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateIsAllDay(Boolean isAllDay) {
        this.isAllDay = isAllDay;
    }
}
