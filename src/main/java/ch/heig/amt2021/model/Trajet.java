package ch.heig.amt2021.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "trajet")
@Entity
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    @JoinColumns({
            @JoinColumn(name = "destination_emplacement_id", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "destination_Station_id", referencedColumnName = "station_id", nullable = false)
    })
    @ManyToOne(optional = false)
    private Emplacement destination;

    @Column(name = "duree")
    private Integer duree;

    @Column(name = "prix", precision = 5, scale = 2)
    private BigDecimal prix;

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Emplacement getDestination() {
        return destination;
    }

    public void setDestination(Emplacement emplacement) {
        this.destination = emplacement;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Integer getId() {
        return id;
    }

    public int getDestination_station_id() {
        return destination.getStationId();
    }

    public int getDestination_emplacement_id() {
        return destination.getId();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVehicule_id() {
        return this.vehicule.getId();
    }
}