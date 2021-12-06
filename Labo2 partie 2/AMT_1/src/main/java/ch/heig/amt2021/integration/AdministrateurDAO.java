package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Administrateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class AdministrateurDAO {

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    public List<Administrateur> getAdmins() {
        List<Administrateur> administrateurs = new ArrayList<>();
        try {
            administrateurs = em.createQuery("SELECT a FROM Administrateur a", Administrateur.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return administrateurs;
    }
}
