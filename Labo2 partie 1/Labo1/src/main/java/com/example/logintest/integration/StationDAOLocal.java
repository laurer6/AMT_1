package com.example.logintest.integration;

import model.Station;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StationDAOLocal {
    List<Station> getStations();
}
