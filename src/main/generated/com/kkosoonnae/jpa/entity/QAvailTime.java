package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAvailTime is a Querydsl query type for AvailTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAvailTime extends EntityPathBase<AvailTime> {

    private static final long serialVersionUID = 1390088029L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAvailTime availTime1 = new QAvailTime("availTime1");

    public final DatePath<java.time.LocalDate> availDate = createDate("availDate", java.time.LocalDate.class);

    public final NumberPath<Integer> availNo = createNumber("availNo", Integer.class);

    public final TimePath<java.time.LocalTime> availTime = createTime("availTime", java.time.LocalTime.class);

    public final QStore store;

    public QAvailTime(String variable) {
        this(AvailTime.class, forVariable(variable), INITS);
    }

    public QAvailTime(Path<? extends AvailTime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAvailTime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAvailTime(PathMetadata metadata, PathInits inits) {
        this(AvailTime.class, metadata, inits);
    }

    public QAvailTime(Class<? extends AvailTime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

