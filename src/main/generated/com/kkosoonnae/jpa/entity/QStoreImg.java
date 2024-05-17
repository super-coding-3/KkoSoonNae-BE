package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreImg is a Querydsl query type for StoreImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreImg extends EntityPathBase<StoreImg> {

    private static final long serialVersionUID = 2062759937L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreImg storeImg = new QStoreImg("storeImg");

    public final StringPath img = createString("img");

    public final NumberPath<Integer> storeImgNo = createNumber("storeImgNo", Integer.class);

    public final QStore storeNo;

    public QStoreImg(String variable) {
        this(StoreImg.class, forVariable(variable), INITS);
    }

    public QStoreImg(Path<? extends StoreImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreImg(PathMetadata metadata, PathInits inits) {
        this(StoreImg.class, metadata, inits);
    }

    public QStoreImg(Class<? extends StoreImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.storeNo = inits.isInitialized("storeNo") ? new QStore(forProperty("storeNo")) : null;
    }

}

