package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerDtl is a Querydsl query type for CustomerDtl
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerDtl extends EntityPathBase<CustomerDtl> {

    private static final long serialVersionUID = -361320481L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerDtl customerDtl = new QCustomerDtl("customerDtl");

    public final StringPath address = createString("address");

    public final StringPath addressDtl = createString("addressDtl");

    public final NumberPath<Integer> cstmrNo = createNumber("cstmrNo", Integer.class);

    public final QCustomerBas customerBas;

    public final StringPath nickName = createString("nickName");

    public final StringPath phone = createString("phone");

    public final StringPath zipCode = createString("zipCode");

    public QCustomerDtl(String variable) {
        this(CustomerDtl.class, forVariable(variable), INITS);
    }

    public QCustomerDtl(Path<? extends CustomerDtl> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerDtl(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerDtl(PathMetadata metadata, PathInits inits) {
        this(CustomerDtl.class, metadata, inits);
    }

    public QCustomerDtl(Class<? extends CustomerDtl> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerBas = inits.isInitialized("customerBas") ? new QCustomerBas(forProperty("customerBas"), inits.get("customerBas")) : null;
    }

}

