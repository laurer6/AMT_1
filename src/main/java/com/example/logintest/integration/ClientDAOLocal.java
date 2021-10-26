package com.example.logintest.integration;

import model.Client;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClientDAOLocal {
    List<Client> getClient();
}
