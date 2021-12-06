package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Prix;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class PrixDAO {

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    public List<Prix> getPrix() {
        List<Prix> prix = new ArrayList<>();
        try {
            prix = em.createQuery("SELECT p FROM Prix p", Prix.class).getResultList();
        }   catch (PersistenceException e){
            Logger.getLogger(PrixDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return prix;
    }
}
