package ch.heig.amt.api.endpoints;

import ch.heig.amt.api.StationsApi;
import ch.heig.amt.api.model.Station;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StationsEndPoint implements StationsApi {

    @Override
    public ResponseEntity<List<Station>> listStationsUsingGET()
    {
        return new ResponseEntity<List<Station>>(List.of() ,HttpStatus.OK);
    }

}
