package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1144220663L;

    public static final QReview review = new QReview("review");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> cstmrNo = createNumber("cstmrNo", Integer.class);

    public final StringPath img = createString("img");

    public final DateTimePath<java.time.LocalDateTime> reviewDt = createDateTime("reviewDt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> reviewNo = createNumber("reviewNo", Integer.class);

    public final StringPath scope = createString("scope");

    public final NumberPath<Integer> storeNo = createNumber("storeNo", Integer.class);

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

