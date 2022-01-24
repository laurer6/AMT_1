package ch.heig.amt.util.response;

import ch.heig.amt.api.model.Vehicle;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReservation {

    private Vehicle vehicle;

    private String from;

    private String to;
}
