package com.example.logintest.integration;

import model.Utilisateur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UtilisateurDAOLocal {
    List<Utilisateur> getUtilisateurs();
    void add(Utilisateur contact);
    Utilisateur getUtilisateur(String login);
    void deleteUtilisateur(int id);
}
