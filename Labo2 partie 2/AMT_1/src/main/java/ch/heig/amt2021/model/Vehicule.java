package ch.heig.amt2021.model;

import javax.persistence.*;

@Table(name = "vehicule")
@Entity
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "matricule", nullable = false, length = 20)
    private String matricule;

    @JoinColumns({
            @JoinColumn(name = "emplacement_id", referencedColumnName = "id"),
            @JoinColumn(name = "station_id", referencedColumnName = "station_id")
    })
    @ManyToOne
    private Emplacement emplacement;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prix_id", nullable = false)
    private Prix prix;

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getStation_id() {
        return emplacement.getStationId();
    }

    public int getEmplacement_id() {
        return emplacement.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategorie() {
        return prix.getCategorie();
    }
}