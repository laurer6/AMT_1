package ch.heig.amt2021.locationapp.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Station implements Serializable {

    private static final long serialVersionUID = 586903987490150223L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String stationId;

    @Column(nullable = false, length = 100, unique = true)
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
