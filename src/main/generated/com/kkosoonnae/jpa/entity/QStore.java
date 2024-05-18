package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -100273054L;

    public static final QStore store = new QStore("store");

    public final StringPath address = createString("address");

    public final StringPath addressDtl = createString("addressDtl");

    public final StringPath content = createString("content");

    public final StringPath phone = createString("phone");

    public final StringPath roadAddress = createString("roadAddress");

    public final StringPath storeName = createString("storeName");

    public final NumberPath<Integer> storeNo = createNumber("storeNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> storeOperDt = createDateTime("storeOperDt", java.time.LocalDateTime.class);

    public final StringPath zipCode = createString("zipCode");

    public QStore(String variable) {
        super(Store.class, forVariable(variable));
    }

    public QStore(Path<? extends Store> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStore(PathMetadata metadata) {
        super(Store.class, metadata);
    }

}

