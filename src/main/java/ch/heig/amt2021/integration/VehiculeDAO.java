package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Vehicule;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class VehiculeDAO{

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    public List<Vehicule> getVehicules() {

        List<Vehicule> vehicules = new ArrayList<>();
        try {
            vehicules = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(VehiculeDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return vehicules;
    }

    public void setEmplacement(int vehiculeId,  int EmplacementID, int StationId) {
        try {

        } catch (PersistenceException e) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Vehicule getVehicule(int id){
        Vehicule vehicule = null;
        try {
            vehicule = (Vehicule) em.createQuery("SELECT v FROM Vehicule v WHERE v.id = :id").setParameter("id", id).getSingleResult();
        } catch (PersistenceException e) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return vehicule;
    }

}

