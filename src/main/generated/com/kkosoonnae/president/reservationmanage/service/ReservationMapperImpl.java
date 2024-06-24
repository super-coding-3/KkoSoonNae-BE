package com.kkosoonnae.president.reservationmanage.service;

import com.kkosoonnae.jpa.entity.Reservation;
import com.kkosoonnae.jpa.entity.ReservationListResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T22:37:33+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationListResponse reservationToReservationListResponse(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationListResponse reservationListResponse = new ReservationListResponse();

        reservationListResponse.setReservationStatus( reservation.getReservationStatus() );

        return reservationListResponse;
    }
}
