package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class PriceEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String category;

    @NonNull
    @Column(nullable = false)
    private Double prix1;

    @NonNull
    @Column(nullable = false)
    private Double prix2;

    @NonNull
    @Column(nullable = false)
    private Double prix3;

    @NonNull
    @Column(nullable = false)
    private BigDecimal priceMin;
}
