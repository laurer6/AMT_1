package ch.heig.amt.repositories;

import ch.heig.amt.entities.PriceEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    PriceEntity findPriceEntityByCategory(String category);

}
