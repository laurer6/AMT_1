package ch.heig.amt.services.impl;

import ch.heig.amt.api.model.ResponseReservation;
import ch.heig.amt.api.model.Vehicle;
import ch.heig.amt.entities.EmplacementEntity;
import ch.heig.amt.entities.ReservationEntity;
import ch.heig.amt.entities.StationEntity;
import ch.heig.amt.entities.VehicleEntity;
import ch.heig.amt.repositories.EmplacementRepository;
import ch.heig.amt.repositories.ReservationRepository;
import ch.heig.amt.repositories.StationRepository;
import ch.heig.amt.repositories.VehicleRepository;
import ch.heig.amt.services.CheckTokenService;
import ch.heig.amt.services.IReservationService;
import ch.heig.amt.util.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private CheckTokenService checkTokenService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmplacementRepository emplacementRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseReservation makeReservation(Integer vehicleId, Integer stationId) {
        UserInfo u =checkTokenService.getUserInfoFromToken("Token");
        if (!u.getCode().equals("ouvert")) {
            throw new RuntimeException("You don't have permission to make a reservation");
        }
        VehicleEntity vehicleEntity = vehicleRepository.findVehicleEntityById(vehicleId);

        if (vehicleEntity == null) {
            throw new RuntimeException("The vehicle id provided doesn't exists");
        }
        StationEntity stationEntity = stationRepository.findStationEntityById(stationId);
        if (stationEntity == null) {
            throw new RuntimeException("The station id provided doesn't exist");
        }
        List<EmplacementEntity> availablesEmplacement = emplacementRepository.findEmplacementEntityByStateAndStation("libre", stationEntity);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleEntity.getId());
        vehicle.setMatricule(vehicleEntity.getMatricule());
        vehicle.setCategorie(Vehicle.CategorieEnum.fromValue(vehicleEntity.getPrice().getCategory()));
        vehicle.setMinPrice(vehicleEntity.getPrice().getPriceMin());

        if (availablesEmplacement.isEmpty()) {
            throw new RuntimeException("Sorry no emplacement for vehicle at the station destination");
        }

        EmplacementEntity vehicleEmpl = emplacementRepository.findEmplacementEntityByVehicle(vehicleEntity);

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setVehicle(vehicleEntity);
        reservationEntity.setDestination(vehicleEmpl);
        reservationRepository.save(reservationEntity);

        ResponseReservation responseReservation = new ResponseReservation();
        responseReservation.setFrom(vehicleEmpl.getStation().getAddress());
        responseReservation.setTo(stationEntity.getAddress());
        responseReservation.setVehicle(vehicle);


        return responseReservation;
    }

    @Override
    public void endReservation(Integer reservationId) {

    }
}
