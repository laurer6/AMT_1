package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class EmplacementEntity {

    /*public enum State {
        Libre, Occupé, Réservé
    }*/
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
        this(station, "Libre");
    }


    public EmplacementEntity(@NonNull StationEntity station, VehicleEntity vehicle) {
        this(station, "Occupé");
        this.vehicle = vehicle;
    }

    public EmplacementEntity(@NonNull StationEntity station, Boolean isReserved) {
        this(station, "Réservé");
    }
}
