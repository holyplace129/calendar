package com.learn.calendar.calendar.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchedule is a Querydsl query type for Schedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchedule extends EntityPathBase<Schedule> {

    private static final long serialVersionUID = -1831888374L;

    public static final QSchedule schedule = new QSchedule("schedule");

    public final com.learn.calendar.common.domain.QBaseTimeEntity _super = new com.learn.calendar.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath deviceToken = createString("deviceToken");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllDay = createBoolean("isAllDay");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<ScheduleDetail, QScheduleDetail> scheduleDetails = this.<ScheduleDetail, QScheduleDetail>createList("scheduleDetails", ScheduleDetail.class, QScheduleDetail.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QSchedule(String variable) {
        super(Schedule.class, forVariable(variable));
    }

    public QSchedule(Path<? extends Schedule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSchedule(PathMetadata metadata) {
        super(Schedule.class, metadata);
    }

}

