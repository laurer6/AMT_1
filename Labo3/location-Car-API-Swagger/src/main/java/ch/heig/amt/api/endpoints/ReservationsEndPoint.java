package ch.heig.amt.api.endpoints;

import ch.heig.amt.api.ReservationsApi;
import ch.heig.amt.api.model.RequestEndReservation;
import ch.heig.amt.api.model.RequestReservation;
import ch.heig.amt.api.model.ResponseEndReservation;
import ch.heig.amt.api.model.ResponseReservation;
import ch.heig.amt.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationsEndPoint implements ReservationsApi {

    @Autowired
    IReservationService reservationService;

    @Override
    public ResponseEntity<ResponseEndReservation> endReservation(RequestEndReservation requestEndReservation) {
        return ReservationsApi.super.endReservation(requestEndReservation);
    }

    @Override
    public ResponseEntity<ResponseReservation> reserveVehicle(RequestReservation requestReservation) {
        ResponseReservation reservation = reservationService.makeReservation(requestReservation.getVehicleId(), requestReservation.getStationIdDestination());

        return new ResponseEntity<>(reservation, HttpStatus.OK);

    }
}
