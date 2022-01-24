package ch.heig.amt.repositories;

import ch.heig.amt.entities.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Integer> {
    StationEntity findStationEntityByAddress(String name);

    StationEntity findStationEntityById(Integer id);
}
