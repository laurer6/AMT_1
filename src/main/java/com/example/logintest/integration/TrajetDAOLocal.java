package com.example.logintest.integration;

import model.Trajet;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrajetDAOLocal {
    List<Trajet> getTrajets();
}
