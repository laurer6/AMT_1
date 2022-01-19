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
    @Column(nullable = false)
    private VehicleEntity vehicle;

    @OneToOne
    @NonNull
    @Column(nullable = false)
    private EmplacementEntity destination;

    private Integer duration;

    private Float price;
}
