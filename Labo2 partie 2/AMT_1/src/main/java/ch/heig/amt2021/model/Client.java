package ch.heig.amt2021.model;

import javax.persistence.*;

@Table(name = "client")
@DiscriminatorValue("CUSTOMER")
@Entity
public class Client extends Utilisateur{
    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;

    @Column(name = "solde", nullable = false, precision = 5, scale = 2)
    private float solde;

    public Client() {
    }

    public Client(int id, Trajet trajet, float solde) {
        this.trajet = trajet;
        this.solde = solde;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
}