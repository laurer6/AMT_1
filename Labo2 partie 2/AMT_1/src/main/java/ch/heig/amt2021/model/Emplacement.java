package ch.heig.amt2021.model;

import javax.persistence.*;

@Table(name = "emplacement")
@Entity
public class Emplacement {
    @EmbeddedId
    private EmplacementId id;

    @MapsId("stationId")
    @OneToOne
    private Station station;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getId() {
        return id.getId();
    }

    public void setId(EmplacementId id) {
        this.id = id;
    }

    public int getStationId() {
        return this.id.getStationId();
    }
}