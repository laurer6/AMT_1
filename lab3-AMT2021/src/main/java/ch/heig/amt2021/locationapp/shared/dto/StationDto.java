package ch.heig.amt2021.locationapp.shared.dto;

import java.io.Serializable;

public class StationDto implements Serializable {

    private static final long serialVersionUID = 5865903039190150223L;
    private long id;            // increment ID coming from database
    private String stationId;   // public id show at user
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}
