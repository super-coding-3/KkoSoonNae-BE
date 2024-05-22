package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -100273054L;

    public static final QStore store = new QStore("store");

    public final DateTimePath<java.time.LocalDateTime> closingTime = createDateTime("closingTime", java.time.LocalDateTime.class);

    public final StringPath content = createString("content");

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lon = createNumber("lon", Double.class);

    public final DateTimePath<java.time.LocalDateTime> openingTime = createDateTime("openingTime", java.time.LocalDateTime.class);

    public final StringPath phone = createString("phone");

    public final StringPath roadAddress = createString("roadAddress");

    public final ListPath<StoreImg, QStoreImg> storeImg = this.<StoreImg, QStoreImg>createList("storeImg", StoreImg.class, QStoreImg.class, PathInits.DIRECT2);

    public final StringPath storeName = createString("storeName");

    public final NumberPath<Integer> storeNo = createNumber("storeNo", Integer.class);

    public final ListPath<Style, QStyle> style = this.<Style, QStyle>createList("style", Style.class, QStyle.class, PathInits.DIRECT2);

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

