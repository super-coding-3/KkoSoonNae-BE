package com.kkosoonnae.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservedPets is a Querydsl query type for ReservedPets
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservedPets extends EntityPathBase<ReservedPets> {

    private static final long serialVersionUID = -327819301L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservedPets reservedPets = new QReservedPets("reservedPets");

    public final QAvailTime avail;

    public final QPet pet;

    public final QReservation reservation;

    public final NumberPath<Integer> reservedPetsNo = createNumber("reservedPetsNo", Integer.class);

    public QReservedPets(String variable) {
        this(ReservedPets.class, forVariable(variable), INITS);
    }

    public QReservedPets(Path<? extends ReservedPets> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservedPets(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservedPets(PathMetadata metadata, PathInits inits) {
        this(ReservedPets.class, metadata, inits);
    }

    public QReservedPets(Class<? extends ReservedPets> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.avail = inits.isInitialized("avail") ? new QAvailTime(forProperty("avail"), inits.get("avail")) : null;
        this.pet = inits.isInitialized("pet") ? new QPet(forProperty("pet"), inits.get("pet")) : null;
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
    }

}

