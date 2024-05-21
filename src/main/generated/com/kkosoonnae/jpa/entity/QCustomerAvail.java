package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomerAvail is a Querydsl query type for CustomerAvail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerAvail extends EntityPathBase<CustomerAvail> {

    private static final long serialVersionUID = 660650546L;

    public static final QCustomerAvail customerAvail = new QCustomerAvail("customerAvail");

    public final NumberPath<Integer> availNo = createNumber("availNo", Integer.class);

    public final NumberPath<Integer> cstmrAvailNo = createNumber("cstmrAvailNo", Integer.class);

    public final NumberPath<Integer> cstmrNo = createNumber("cstmrNo", Integer.class);

    public final NumberPath<Integer> reservationNo = createNumber("reservationNo", Integer.class);

    public QCustomerAvail(String variable) {
        super(CustomerAvail.class, forVariable(variable));
    }

    public QCustomerAvail(Path<? extends CustomerAvail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomerAvail(PathMetadata metadata) {
        super(CustomerAvail.class, metadata);
    }

}

