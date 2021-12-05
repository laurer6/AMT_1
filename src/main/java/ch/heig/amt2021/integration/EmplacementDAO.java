package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Emplacement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EmplacementDAO {

    private static final Logger LOGGER = Logger.getLogger(StationDAO.class.getName());

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    public List<Emplacement> getEmplacements() {

        List<Emplacement> emplacements = null;

        try {
            emplacements =  em.createQuery("SELECT e FROM Emplacement e", Emplacement.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(EmplacementDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return emplacements;
    }
}
