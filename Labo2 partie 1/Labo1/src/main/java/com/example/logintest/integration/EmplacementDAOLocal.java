package com.example.logintest.integration;

import model.Emplacement;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EmplacementDAOLocal {
    List<Emplacement> getEmplacements();
}
