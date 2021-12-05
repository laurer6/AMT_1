package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UtilisateurDAO {

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(UtilisateurDAO.class.getName());
    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            utilisateurs = em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return utilisateurs;
    }

    public void add(Utilisateur utilisateur) {
        try {
            em.persist(utilisateur);
        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Utilisateur getUtilisateur(String login){
        Utilisateur utilisateur1 = null;
        try {

        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return utilisateur1;
    }

    public void deleteUtilisateur(int id){
        try {

        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
