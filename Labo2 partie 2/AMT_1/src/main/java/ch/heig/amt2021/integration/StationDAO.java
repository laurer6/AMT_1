package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Station;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class StationDAO {

    private static final Logger LOGGER = Logger.getLogger(StationDAO.class.getName());

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    public List<Station> getStations() {
        List<Station> stations = null;
        try {
            stations = em.createQuery("SELECT s FROM Station s", Station.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return stations;
    }
}
