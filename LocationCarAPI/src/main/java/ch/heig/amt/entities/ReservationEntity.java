package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class ReservationEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @OneToOne
    private VehicleEntity vehicle;

    @NonNull
    @OneToOne
    private EmplacementEntity destination;

    private Integer duration;

    private Float price;
}
