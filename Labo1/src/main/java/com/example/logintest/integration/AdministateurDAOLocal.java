package com.example.logintest.integration;

import model.Administrateur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AdministateurDAOLocal {
    List<Administrateur> getAdmin();
}
