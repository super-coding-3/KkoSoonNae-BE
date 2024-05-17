package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreLocation is a Querydsl query type for StoreLocation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreLocation extends EntityPathBase<StoreLocation> {

    private static final long serialVersionUID = -1775339977L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreLocation storeLocation = new QStoreLocation("storeLocation");

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Integer> locationNo = createNumber("locationNo", Integer.class);

    public final NumberPath<Double> lon = createNumber("lon", Double.class);

    public final QStore store;

    public QStoreLocation(String variable) {
        this(StoreLocation.class, forVariable(variable), INITS);
    }

    public QStoreLocation(Path<? extends StoreLocation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreLocation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreLocation(PathMetadata metadata, PathInits inits) {
        this(StoreLocation.class, metadata, inits);
    }

    public QStoreLocation(Class<? extends StoreLocation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

