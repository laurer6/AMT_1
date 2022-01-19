package ch.heig.amt.configuration;

import ch.heig.amt.entities.*;
import ch.heig.amt.entities.EmplacementEntity.State;
import ch.heig.amt.entities.PriceEntity.Category;
import ch.heig.amt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitDataForAPI {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmplacementRepository emplacementRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public void generateDataForStations() {
        stationRepository.saveAll(List.of(
                new StationEntity("Rue du lac 6"),
                new StationEntity("Rue du Milieu 18"),
                new StationEntity("Route de lausanne 10"),
                new StationEntity("Rue de l'industrie 14"),
                new StationEntity("Rue d'Orbe 51")
        ));
    }

    public void generateDataForPrice() {
        priceRepository.saveAll(List.of(
                new PriceEntity(Category.Berline, 2.95, 2.60, 2.30, 50.0),
                new PriceEntity(Category.Moto, 1.70, 1.50, 1.50, 35.0),
                new PriceEntity(Category.Fourgon, 3.60, 3.00, 2.80, 100.0)
        ));
    }

    public void generateDataForVehicle() {
        vehicleRepository.saveAll(List.of(
                new VehicleEntity("VD 364 263", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 536 245", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 873 422", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 969 142", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 153 758", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 542 436", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 211 738", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 532 374", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 645 555", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 786 588", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 873 041", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 176 591", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 793 272", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 635 242", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 436 502", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 825 332", priceRepository.findPriceEntityByCategory(Category.Moto)),
                new VehicleEntity("VD 645 322", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 245 153", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 421 482", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 587 428", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 921 351", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 921 118", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 268 402", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 008 276", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 241 522", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 291 853", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 458 635", priceRepository.findPriceEntityByCategory(Category.Fourgon)),
                new VehicleEntity("VD 426 529", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 652 288", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 425 321", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 577 929", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 894 303", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 318 843", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 894 263", priceRepository.findPriceEntityByCategory(Category.Berline)),
                new VehicleEntity("VD 436 508", priceRepository.findPriceEntityByCategory(Category.Berline))
        ));
    }

    public void generateDataForEmplacement() {
        emplacementRepository.saveAll(List.of(
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 176 591")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 436 502")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 921 118")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 008 276")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 291 853")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 577 929")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 894 303")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 318 843")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 894 263")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 436 508")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du lac 6")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 364 263")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 536 245")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 873 422")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 969 142")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 645 555")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 786 588")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 873 041")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 645 322")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 245 153")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 421 482")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 587 428")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 921 351")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue de l'industrie 14"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 241 522")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 153 758")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 542 436")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 635 242")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 825 332")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 268 402")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 425 321")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Route de lausanne 10")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du Milieu 18"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 211 738")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du Milieu 18"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 532 374")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du Milieu 18"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 793 272")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du Milieu 18"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 458 635")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du Milieu 18"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 426 529")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue du Milieu 18"), State.Occupé, vehicleRepository.findVehicleEntityByMatricule("VD 652 288")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue d'Orbe 51"), true),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue d'Orbe 51")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue d'Orbe 51")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue d'Orbe 51")),
                new EmplacementEntity(stationRepository.findStationEntityByAddress("Rue d'Orbe 51"))
        ));
    }

    public void generateDataForReservation() {
        reservationRepository.saveAll(List.of(
                new ReservationEntity(
                        emplacementRepository.findEmplacementEntityByStateAndStation(
                                State.Occupé,
                                stationRepository.findStationEntityByAddress("Rue de l'industrie 14")).get(0).getVehicle(),
                        emplacementRepository.findEmplacementEntityByStateAndStation(
                                State.Réservé,
                                stationRepository.findStationEntityByAddress("Rue d'Orbe 51")).get(0))
        ));
    }

}
