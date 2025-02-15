package com.learn.calendar.calendar.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScheduleDetail is a Querydsl query type for ScheduleDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScheduleDetail extends EntityPathBase<ScheduleDetail> {

    private static final long serialVersionUID = 100757243L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScheduleDetail scheduleDetail = new QScheduleDetail("scheduleDetail");

    public final StringPath description = createString("description");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAlarmEnabled = createBoolean("isAlarmEnabled");

    public final QSchedule schedule;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public QScheduleDetail(String variable) {
        this(ScheduleDetail.class, forVariable(variable), INITS);
    }

    public QScheduleDetail(Path<? extends ScheduleDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScheduleDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScheduleDetail(PathMetadata metadata, PathInits inits) {
        this(ScheduleDetail.class, metadata, inits);
    }

    public QScheduleDetail(Class<? extends ScheduleDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule")) : null;
    }

}

