package ch.heig.amt2021.model;

import javax.persistence.*;

@Table(name = "prix")
@Entity
public class Prix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "categorie", nullable = false)
    private String categorie;

    @Column(name = "prix1", nullable = false)
    private float prix1;

    @Column(name = "prix2", nullable = false)
    private float prix2;

    @Column(name = "prix3", nullable = false)
    private float prix3;

    public float getPrix3() {
        return prix3;
    }

    public void setPrix3(float prix3) {
        this.prix3 = prix3;
    }

    public float getPrix2() {
        return prix2;
    }

    public void setPrix2(float prix2) {
        this.prix2 = prix2;
    }

    public float getPrix1() {
        return prix1;
    }

    public void setPrix1(float prix1) {
        this.prix1 = prix1;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}