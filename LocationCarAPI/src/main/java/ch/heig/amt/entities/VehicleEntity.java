package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class VehicleEntity {
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(unique = true)
    private String matricule;

    @NonNull
    @OneToOne
    private PriceEntity price;
}
