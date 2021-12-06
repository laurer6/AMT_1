package com.example.logintest.integration;

import model.Prix;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PrixDAOLocal {
    List<Prix> getPrix();
}
