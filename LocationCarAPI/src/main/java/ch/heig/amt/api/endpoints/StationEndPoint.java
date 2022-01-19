package ch.heig.amt.api.endpoints;

import ch.heig.amt.api.StationsApi;
import ch.heig.amt.api.model.Station;
import ch.heig.amt.entities.StationEntity;
import ch.heig.amt.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StationEndPoint implements StationsApi {

    @Autowired
    private StationRepository stationRepository;

    public ResponseEntity<List<ch.heig.amt.api.model.Station>> listStationsUsingGET () {
        List<StationEntity> stationEntities = stationRepository.findAll();

        List<Station> stations = stationEntities.stream().map(stationEntity -> {
            ch.heig.amt.api.model.Station station = new Station();
            station.setId(stationEntity.getId());
            station.setAdresse(stationEntity.getAddress());

            return station;
        }).collect(Collectors.toCollection(ArrayList::new));

        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    public ResponseEntity<Void> addStationUsingPost(Station station) {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setAddress(station.getAdresse());

        StationEntity addedStation = stationRepository.save(stationEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(addedStation.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
