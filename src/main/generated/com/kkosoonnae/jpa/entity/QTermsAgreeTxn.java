package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTermsAgreeTxn is a Querydsl query type for TermsAgreeTxn
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTermsAgreeTxn extends EntityPathBase<TermsAgreeTxn> {

    private static final long serialVersionUID = 573630790L;

    public static final QTermsAgreeTxn termsAgreeTxn = new QTermsAgreeTxn("termsAgreeTxn");

    public final DateTimePath<java.time.LocalDateTime> agreeDt = createDateTime("agreeDt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> agreeNo = createNumber("agreeNo", Integer.class);

    public final StringPath agreeYn = createString("agreeYn");

    public final NumberPath<Integer> cstmrNo = createNumber("cstmrNo", Integer.class);

    public final NumberPath<Integer> termNo = createNumber("termNo", Integer.class);

    public QTermsAgreeTxn(String variable) {
        super(TermsAgreeTxn.class, forVariable(variable));
    }

    public QTermsAgreeTxn(Path<? extends TermsAgreeTxn> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTermsAgreeTxn(PathMetadata metadata) {
        super(TermsAgreeTxn.class, metadata);
    }

}

