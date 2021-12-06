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

    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs = null;
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

    public Utilisateur getUtilisateurByLogin(String login){
        Utilisateur utilisateur = null;
        try {
            utilisateur = (Utilisateur) em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login").setParameter("login", login).getSingleResult();
        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return utilisateur;
    }

    public Utilisateur getUtilisateurById(int id){
        Utilisateur utilisateur = null;
        try {
            utilisateur = (Utilisateur) em.createQuery("SELECT u FROM Utilisateur u WHERE u.id = :id").setParameter("id", id).getSingleResult();
        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return utilisateur;
    }

    public void deleteUtilisateur(int id){
        try {
            em.remove(getUtilisateurById(id));
        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
