package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerBas is a Querydsl query type for CustomerBas
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerBas extends EntityPathBase<CustomerBas> {

    private static final long serialVersionUID = -361322985L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerBas customerBas = new QCustomerBas("customerBas");

    public final DateTimePath<java.time.LocalDateTime> createDt = createDateTime("createDt", java.time.LocalDateTime.class);

    public final EnumPath<RoleType> cstmrDivCd = createEnum("cstmrDivCd", RoleType.class);

    public final NumberPath<Integer> cstmrNo = createNumber("cstmrNo", Integer.class);

    public final QCustomerDtl customerDtl;

    public final StringPath email = createString("email");

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public QCustomerBas(String variable) {
        this(CustomerBas.class, forVariable(variable), INITS);
    }

    public QCustomerBas(Path<? extends CustomerBas> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerBas(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerBas(PathMetadata metadata, PathInits inits) {
        this(CustomerBas.class, metadata, inits);
    }

    public QCustomerBas(Class<? extends CustomerBas> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerDtl = inits.isInitialized("customerDtl") ? new QCustomerDtl(forProperty("customerDtl"), inits.get("customerDtl")) : null;
    }

}

