package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class PriceEntity {

    public enum Category {
        Berline, Fourgon, Moto
    }

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @Column(columnDefinition = "ENUM('Berline', 'Fourgon', 'Moto')", nullable = false)
    private Category category;

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
    private Double priceMin;
}
