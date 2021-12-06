package ch.heig.amt2021.integration;

import ch.heig.amt2021.model.Client;
import ch.heig.amt2021.model.Trajet;
import ch.heig.amt2021.model.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ClientDAO {

    private static final Logger LOGGER = Logger.getLogger(StationDAO.class.getName());

    @PersistenceContext(unitName = "locationCarPersistenceUnit")
    private EntityManager em;

    public List<Client> getClient() {
        List<Client> clients = new ArrayList<>();
        try {
            clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return clients;
    }

    public Client getClient(int utilisateurId) {
        return em.createQuery("SELECT c FROM Client c WHERE c.id = :id", Client.class).setParameter("id", utilisateurId).getSingleResult();
    }

    public void setTrajet(Trajet trajet, int userID) {
        Client client = getClient(userID);

        client.setTrajet(trajet);

        try {
            em.merge(client);
        } catch (PersistenceException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void setSolde(float montant, int userID) {
        Client client = getClient(userID);

        client.setSolde(montant);

        em.merge(client);
    }

    public void addClient(int id) {
        Client client = new Client(id, null, 0.0f);
        em.persist(client);
    }

    public void deleteClient(int utilisateur_id) {
        Client client = getClient(utilisateur_id);
        em.remove(client);
    }

    public void deleteTrajet(int clientID) {
        Client client = getClient(clientID);

        client.setTrajet(null);

        em.merge(client);
    }
}
