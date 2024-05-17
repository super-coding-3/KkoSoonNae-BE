package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomerBas is a Querydsl query type for CustomerBas
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerBas extends EntityPathBase<CustomerBas> {

    private static final long serialVersionUID = -361322985L;

    public static final QCustomerBas customerBas = new QCustomerBas("customerBas");

    public final DateTimePath<java.time.LocalDateTime> createDt = createDateTime("createDt", java.time.LocalDateTime.class);

    public final EnumPath<RoleType> cstmrDivCd = createEnum("cstmrDivCd", RoleType.class);

    public final NumberPath<Integer> cstmrNo = createNumber("cstmrNo", Integer.class);

    public final StringPath email = createString("email");

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public QCustomerBas(String variable) {
        super(CustomerBas.class, forVariable(variable));
    }

    public QCustomerBas(Path<? extends CustomerBas> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomerBas(PathMetadata metadata) {
        super(CustomerBas.class, metadata);
    }

}

