package ch.heig.amt.util.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestReservation {
    private Long vehicleId;

    private Long stationIdDestination;
}
