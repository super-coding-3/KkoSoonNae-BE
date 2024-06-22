package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPet is a Querydsl query type for Pet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPet extends EntityPathBase<Pet> {

    private static final long serialVersionUID = -245917472L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPet pet = new QPet("pet");

    public final DatePath<java.time.LocalDate> birthDt = createDate("birthDt", java.time.LocalDate.class);

    public final QCustomerBas customerBas;

    public final StringPath gender = createString("gender");

    public final StringPath img = createString("img");

    public final StringPath mainPet = createString("mainPet");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> petNo = createNumber("petNo", Integer.class);

    public final StringPath type = createString("type");

    public final StringPath weight = createString("weight");

    public QPet(String variable) {
        this(Pet.class, forVariable(variable), INITS);
    }

    public QPet(Path<? extends Pet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPet(PathMetadata metadata, PathInits inits) {
        this(Pet.class, metadata, inits);
    }

    public QPet(Class<? extends Pet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerBas = inits.isInitialized("customerBas") ? new QCustomerBas(forProperty("customerBas"), inits.get("customerBas")) : null;
    }

}

