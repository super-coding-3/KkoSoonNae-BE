package com.kkosoonnae.president.reservationmanage.service;

import com.kkosoonnae.jpa.entity.Reservation;
import com.kkosoonnae.jpa.entity.ReservationListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

//    ReservationListResponse reservationToReservationListResponse(Reservation reservation);


}
