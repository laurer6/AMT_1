package com.example.logintest.integration;

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
public class UtilisateurDAO implements UtilisateurDAOLocal{

    //@Resource(lookup = "java:global/ContactsInMem") // pour SGBD embarqué (H2)
    @Resource(lookup = "jdbc/Lab1_AMT")  // pour SGBD externe
    private DataSource dataSource;

    /**
     * Création table contacts
     *   Le datasource doit être défini et le schéma doit exister
     *   (utilisé en phase développement seulement)
     */

    public void create() {
        /*
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement
                        ("create table contacts (" +
                                "nom varchar(20) primary key," +
                                "telephone int," +
                                "conjoint varchar(20) references contacts)");){
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        */
    }

    /**
     * Liste de tous les contacts
     */
    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM utilisateur");) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt(1);
                String login1 = rs.getString(2);
                String password1 = rs.getString(3);
                Utilisateur utilisateur1 = new Utilisateur(id1,login1,password1);


                utilisateurs.add(utilisateur1);
            }
        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return utilisateurs;
    }

    /**
     * Ajoute un nouveau contact (sans conjoint)
     *   (doublons encore à gérer)
     */
    public void add(Utilisateur utilisateur) {
        /*
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement
                        ("insert into contacts (nom,telephone) values (?,?)");){
            pstmt.setInt(1,utilisateur.getID());
            pstmt.setString(2,utilisateur.getLogin());
            pstmt.setString(3,utilisateur.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        */
    }

    /**
     *  Met à jour un Contact
     *    (mise à jour du conjoint seulement)
     */
    /*
    public void updateConjoint(Utilisateur contact) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement
                        ("update contacts set conjoint=? where nom=?");){
            pstmt.setString(1,contact.getConjoint().getNom());
            pstmt.setString(2,contact.getNom());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

     */



    /**
     * Obtention d'un contact
     *   obtention du contact conjoint s'il existe
     *   (contact inconnu encore à gérer)
     */

    public Utilisateur getUtilisateur(String nom) {
        /*
        Utilisateur utilisateur1 = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM utilisateur1 c1 " +
                             "where c1.nom=?");) {
            pstmt.setString(1,nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String login1 = rs.getString(1);
                int id1 = rs.getInt(2);
                String password1 = rs.getString(3);
                utilisateur1 = new Utilisateur(id1,login1,password1);

            }
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return utilisateur1;

         */
        return null;
    }


}
