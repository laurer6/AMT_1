package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Trajet;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class TrajetDAO {

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    public List<Trajet> getTrajets() {
        List<Trajet> trajets = new ArrayList<>();
        try {
            trajets = em.createQuery("SELECT t FROM Trajet t", Trajet.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(TrajetDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return trajets;
    }

    public void addTrajet(int vehiculeID, int emplacementDestination,int stationDestination) {
        try {

        } catch (PersistenceException e) {
            Logger.getLogger(TrajetDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void supTrajet(int id) {
        try {

        } catch (PersistenceException e) {
            Logger.getLogger(TrajetDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Trajet getTrajetViaVehicule(int vehiculeID){

            Trajet trajet = null;
            try {
                trajet = (Trajet) em.createQuery("SELECT t FROM Trajet t WHERE t.id = :vehiculeID").setParameter("vehiculeID", vehiculeID).getSingleResult();
            } catch (PersistenceException e) {
                Logger.getLogger(TrajetDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            return trajet;
        }
}
