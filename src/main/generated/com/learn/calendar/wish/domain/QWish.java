package com.learn.calendar.wish.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWish is a Querydsl query type for Wish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWish extends EntityPathBase<Wish> {

    private static final long serialVersionUID = -1867678205L;

    public static final QWish wish = new QWish("wish");

    public final com.learn.calendar.common.domain.QBaseTimeEntity _super = new com.learn.calendar.common.domain.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> createAt = createDate("createAt", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> currentAmount = createNumber("currentAmount", Long.class);

    public final NumberPath<Long> dayDeposit = createNumber("dayDeposit", Long.class);

    public final DatePath<java.time.LocalDate> expirationAt = createDate("expirationAt", java.time.LocalDate.class);

    public final EnumPath<Frequency> frequency = createEnum("frequency", Frequency.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image = createString("image");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final DatePath<java.time.LocalDate> modifyAt = createDate("modifyAt", java.time.LocalDate.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final DatePath<java.time.LocalDate> startAt = createDate("startAt", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public QWish(String variable) {
        super(Wish.class, forVariable(variable));
    }

    public QWish(Path<? extends Wish> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWish(PathMetadata metadata) {
        super(Wish.class, metadata);
    }

}

