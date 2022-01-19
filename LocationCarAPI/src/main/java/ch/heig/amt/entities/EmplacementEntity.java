package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class EmplacementEntity {
    public enum State {
        Libre, Occupé, Réservé
    }
    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @OneToOne
    private StationEntity station;

    @NonNull
    @Column(columnDefinition = "ENUM('Libre', 'Occupé', 'Réservé') default 'Libre'")
    private State state;

    @NonNull
    @OneToOne
    private VehicleEntity vehicle;

    public EmplacementEntity(@NonNull StationEntity station) {
        this(station, State.Libre, null);
    }

    public EmplacementEntity(@NonNull StationEntity station, Boolean isReserved) {
        this(station, State.Réservé, null);
    }
}
