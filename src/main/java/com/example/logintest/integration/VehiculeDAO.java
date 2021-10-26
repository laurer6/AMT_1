package com.example.logintest.integration;

import model.Station;
import model.Vehicule;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class VehiculeDAO implements VehiculeDAOLocal {

    @Resource(lookup = "jdbc/Lab1_AMT")  // pour SGBD externe
    private DataSource dataSource;

    @Override
    public List<Vehicule> getVehicule() {

        List<Vehicule> vehicules = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM vehicule");){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){

                int id1 = rs.getInt(1);
                String matricule1 = rs.getString(2);
                int emplacement_id1 = rs.getInt(3);
                int station_id1 = rs.getInt(4);
                String categorie1 = rs.getString(5);

                Vehicule vehicule1 = new Vehicule(id1,matricule1,emplacement_id1,station_id1,categorie1);

                vehicules.add(vehicule1);
            }
        }   catch (SQLException e){
            Logger.getLogger(VehiculeDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return vehicules;


    }
}
