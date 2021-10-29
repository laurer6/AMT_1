package com.example.logintest.integration;

import com.example.logintest.bean.UserAccount;
import model.Client;
import model.Trajet;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClientDAOLocal {
    List<Client> getClient();
    void setTrajet(int trajet, int user);
    void deleteTrajet(int userID);
    void setSolde(float montant, int userID);
    Client getClient(int id);
    void addClient(int id);
}
