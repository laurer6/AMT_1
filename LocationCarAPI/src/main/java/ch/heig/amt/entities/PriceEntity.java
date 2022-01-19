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

    /*public enum Category {
        Berline, Fourgon, Moto
    }*/

    /*public PriceEntity(String category, double prix1, double prix2, double prix3, double priceMin) {
        this(Category.valueOf(category), prix1, prix2, prix3, priceMin);
    }*/

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
    private Double priceMin;
}
