package ch.heig.amt.repositories;

import ch.heig.amt.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {
    VehicleEntity findVehicleEntityByMatricule(String matricule);
}
