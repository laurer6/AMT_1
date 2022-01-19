package ch.heig.amt.repositories;

import ch.heig.amt.entities.EmplacementEntity;
// import ch.heig.amt.entities.EmplacementEntity.State;
import ch.heig.amt.entities.StationEntity;
import ch.heig.amt.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmplacementRepository extends JpaRepository<EmplacementEntity, Integer> {
    List<EmplacementEntity> findEmplacementEntityByStateAndStation(String state, StationEntity station);

    EmplacementEntity findEmplacementEntityByVehicle(VehicleEntity vehicle);
}
