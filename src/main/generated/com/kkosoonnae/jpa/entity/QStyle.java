package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyle is a Querydsl query type for Style
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyle extends EntityPathBase<Style> {

    private static final long serialVersionUID = -100263630L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyle style = new QStyle("style");

    public final StringPath img = createString("img");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final QStore store;

    public final StringPath styleName = createString("styleName");

    public final NumberPath<Integer> styleNo = createNumber("styleNo", Integer.class);

    public QStyle(String variable) {
        this(Style.class, forVariable(variable), INITS);
    }

    public QStyle(Path<? extends Style> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyle(PathMetadata metadata, PathInits inits) {
        this(Style.class, metadata, inits);
    }

    public QStyle(Class<? extends Style> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

