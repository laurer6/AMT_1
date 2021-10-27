package com.example.logintest.integration;

import com.example.logintest.bean.UserAccount;
import model.Client;
import model.Trajet;
import model.Utilisateur;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
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

    @Override
        public void addTrajet(int trajetId, int userID){
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement pstmt = connection.prepareStatement
                    ("update client set trajet_id=? where utilisateur_id =?");){
                pstmt.setInt(1, trajetId);
                pstmt.setInt(2, userID);
                pstmt.executeUpdate();
            }catch(SQLException e){
        Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
    }


        }
    @Override
        public void deleteTrajet(int userID) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement pstmt = connection.prepareStatement
                            ("update client set trajet_id=? where utilisateur_id =?");) {
                pstmt.setNull(1, Types.NULL);
                pstmt.setInt(2, userID);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }


}
