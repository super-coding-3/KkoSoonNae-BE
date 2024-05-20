package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 1038879607L;

    public static final QNotice notice = new QNotice("notice");

    public final StringPath noticeContent = createString("noticeContent");

    public final StringPath noticeDate = createString("noticeDate");

    public final NumberPath<Integer> noticeNo = createNumber("noticeNo", Integer.class);

    public final StringPath noticeTitle = createString("noticeTitle");

    public final StringPath noticeViewCount = createString("noticeViewCount");

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}

