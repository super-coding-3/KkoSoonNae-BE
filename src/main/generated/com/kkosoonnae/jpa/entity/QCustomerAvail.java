package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerAvail is a Querydsl query type for CustomerAvail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerAvail extends EntityPathBase<CustomerAvail> {

    private static final long serialVersionUID = 660650546L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerAvail customerAvail = new QCustomerAvail("customerAvail");

    public final NumberPath<Integer> availNo = createNumber("availNo", Integer.class);

    public final NumberPath<Integer> cstmrAvailNo = createNumber("cstmrAvailNo", Integer.class);

    public final QCustomerBas cstmrNo;

    public final QReservation reservationNo;

    public QCustomerAvail(String variable) {
        this(CustomerAvail.class, forVariable(variable), INITS);
    }

    public QCustomerAvail(Path<? extends CustomerAvail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerAvail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerAvail(PathMetadata metadata, PathInits inits) {
        this(CustomerAvail.class, metadata, inits);
    }

    public QCustomerAvail(Class<? extends CustomerAvail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cstmrNo = inits.isInitialized("cstmrNo") ? new QCustomerBas(forProperty("cstmrNo")) : null;
        this.reservationNo = inits.isInitialized("reservationNo") ? new QReservation(forProperty("reservationNo"), inits.get("reservationNo")) : null;
    }

}

