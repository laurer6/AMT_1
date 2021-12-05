package ch.heig.amt2021.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmplacementId implements Serializable {
    private static final long serialVersionUID = 274623388861060791L;
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "station_id", nullable = false)
    private Integer stationId;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmplacementId entity = (EmplacementId) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.stationId, entity.stationId);
    }
}