package com.example.logintest.integration;

import model.Client;
import model.Utilisateur;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ClientDAO implements ClientDAOLocal{

    @Resource(lookup = "jdbc/Lab1_AMT")  // pour SGBD externe
    private DataSource dataSource;


    @Override
    public List<Client> getClient() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM client");) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int utilisateur_id1 = rs.getInt(1);
                int trajet_id1 = rs.getInt(2);
                int solde1 = rs.getInt(3);
                Client client = new Client(utilisateur_id1,trajet_id1,solde1);
                clients.add(client);
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return clients;
        }
}
