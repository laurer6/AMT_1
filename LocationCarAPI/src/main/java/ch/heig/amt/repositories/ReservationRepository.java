package ch.heig.amt.repositories;

import ch.heig.amt.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    ReservationEntity findReservationEntityById(Integer id);
}
