package ch.heig.amt2021.locationapp;

import ch.heig.amt2021.locationapp.io.entity.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {

    Station findByAddress(String address);
}
