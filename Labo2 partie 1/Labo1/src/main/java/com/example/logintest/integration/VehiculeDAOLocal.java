package com.example.logintest.integration;

import model.Vehicule;

import javax.ejb.Local;
import java.util.List;

@Local
public interface VehiculeDAOLocal {
    List<Vehicule> getVehiculeViaID();
    void setEmplacement(int id, int noEmplacementDepart, int numeroStationDepart);
    Vehicule getVehiculeViaID(int id);
}
