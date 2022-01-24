package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class EmplacementEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @OneToOne
    private StationEntity station;

    @NonNull
    @Column(nullable = false)
    private String state;

    @OneToOne
    private VehicleEntity vehicle;

    public EmplacementEntity(@NonNull StationEntity station) {
        this(station, "libre");
    }


    public EmplacementEntity(@NonNull StationEntity station, VehicleEntity vehicle) {
        this(station, "occupé");
        this.vehicle = vehicle;
    }

    public EmplacementEntity(@NonNull StationEntity station, Boolean isReserved) {
        this(station, "réservé");
    }
}
