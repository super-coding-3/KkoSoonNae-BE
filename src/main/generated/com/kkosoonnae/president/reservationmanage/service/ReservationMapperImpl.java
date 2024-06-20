package com.kkosoonnae.president.reservationmanage.service;

import com.kkosoonnae.jpa.entity.Reservation;
import com.kkosoonnae.jpa.entity.ReservationListResponse;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T03:10:38+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationListResponse reservationToReservationListResponse(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationListResponse reservationListResponse = new ReservationListResponse();

        reservationListResponse.setReservationNo( reservation.getReservationNo() );
        if ( reservation.getReservationDate() != null ) {
            reservationListResponse.setReservationDate( DateTimeFormatter.ISO_LOCAL_DATE.format( reservation.getReservationDate() ) );
        }
        reservationListResponse.setReservationStatus( reservation.getReservationStatus() );

        return reservationListResponse;
    }
}
