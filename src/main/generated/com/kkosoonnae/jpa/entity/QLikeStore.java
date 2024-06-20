package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeStore is a Querydsl query type for LikeStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeStore extends EntityPathBase<LikeStore> {

    private static final long serialVersionUID = 1949684363L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeStore likeStore = new QLikeStore("likeStore");

    public final DateTimePath<java.time.LocalDateTime> createDt = createDateTime("createDt", java.time.LocalDateTime.class);

    public final QCustomerBas customerBas;

    public final NumberPath<Integer> likeNo = createNumber("likeNo", Integer.class);

    public final QStore store;

    public QLikeStore(String variable) {
        this(LikeStore.class, forVariable(variable), INITS);
    }

    public QLikeStore(Path<? extends LikeStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeStore(PathMetadata metadata, PathInits inits) {
        this(LikeStore.class, metadata, inits);
    }

    public QLikeStore(Class<? extends LikeStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerBas = inits.isInitialized("customerBas") ? new QCustomerBas(forProperty("customerBas"), inits.get("customerBas")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

