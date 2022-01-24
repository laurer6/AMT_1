package ch.heig.amt.api.endpoints;

import ch.heig.amt.api.StationsApi;
import ch.heig.amt.api.model.Emplacement;
import ch.heig.amt.api.model.Emplacement.StatutEnum;
import ch.heig.amt.api.model.Vehicle;
import ch.heig.amt.entities.EmplacementEntity;
import ch.heig.amt.entities.StationEntity;
import ch.heig.amt.api.model.Station;
import ch.heig.amt.entities.VehicleEntity;
import ch.heig.amt.repositories.EmplacementRepository;
import ch.heig.amt.repositories.StationRepository;
import ch.heig.amt.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StationsEndPoint implements StationsApi {

    @Autowired
    private EmplacementRepository emplacementRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public ResponseEntity<List<Station>> listStationsUsingGET()
    {
        // On récupère toutes les stations en base de données
        List<StationEntity> stationEntities = stationRepository.findAll();


        List<Station> stations = stationEntities.stream().map(stationEntity -> {
            Station station = new Station();
            station.setAddress(stationEntity.getAddress());
            List<EmplacementEntity> emplacementEntities = emplacementRepository.findEmplacementEntityByStation(stationEntity);
            List<Emplacement> emplacements = emplacementEntities.stream().map(emplacementEntity -> {
                Emplacement emplacement = new Emplacement();
                emplacement.setNumero(emplacementEntity.getId());
                if (emplacementEntity.getVehicle() == null) {
                    emplacement.setVehicle(null);
                } else {
                    VehicleEntity vehicleEntity = emplacementEntity.getVehicle();
                    Vehicle vehicle = new Vehicle();
                    vehicle.setMatricule(vehicleEntity.getMatricule());
                    vehicle.setId(vehicleEntity.getId());
                    vehicle.setCategorie(Vehicle.CategorieEnum.fromValue(vehicleEntity.getPrice().getCategory()));
                    vehicle.setMinPrice(vehicleEntity.getPrice().getPriceMin());
                    emplacement.setVehicle(vehicle);
                }
                emplacement.setStatut(StatutEnum.fromValue(emplacementEntity.getState()));
                return emplacement;
            }).collect(Collectors.toCollection(ArrayList::new));
            station.setEmplacements(emplacements);
            return station;
        }).collect(Collectors.toCollection(ArrayList::new));


        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

}
