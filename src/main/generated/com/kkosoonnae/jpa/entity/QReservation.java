package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -152315251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final QAvailTime avail;

    public final QCustomerBas cstmrBas;

    public final StringPath feature = createString("feature");

    public final DatePath<java.time.LocalDate> reservationDate = createDate("reservationDate", java.time.LocalDate.class);

    public final NumberPath<Integer> reservationNo = createNumber("reservationNo", Integer.class);

    public final StringPath reservationStatus = createString("reservationStatus");

    public final TimePath<java.time.LocalTime> reservationTime = createTime("reservationTime", java.time.LocalTime.class);

    public final QStore store;

    public final StringPath styleName = createString("styleName");

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.avail = inits.isInitialized("avail") ? new QAvailTime(forProperty("avail"), inits.get("avail")) : null;
        this.cstmrBas = inits.isInitialized("cstmrBas") ? new QCustomerBas(forProperty("cstmrBas")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

