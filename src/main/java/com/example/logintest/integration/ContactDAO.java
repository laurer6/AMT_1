package com.example.logintest.integration;

import model.Contact;

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
public class ContactDAO implements ContactDAOLocal {

    //@Resource(lookup = "java:global/ContactsInMem") // pour SGBD embarqué (H2)
    @Resource(lookup = "jdbc/ContactsDS")  // pour SGBD externe
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
                                "telephone int");){
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }

         */
    }

    /**
     * Liste de tous les contacts
     */
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM contacts");) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nom1 = rs.getString(1);
                int telephone1 = rs.getInt(2);
                Contact contact1 = new Contact(nom1,telephone1);


                contacts.add(contact1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contacts;
    }

    /**
     * Ajoute un nouveau contact (sans conjoint)
     *   (doublons encore à gérer)
     */
    public void add(Contact contact) {
        /*
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement
                        ("insert into contacts (nom,telephone) values (?,?)");){
            pstmt.setString(1,contact.getNom());
            pstmt.setInt(2,contact.getTelephone());
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
    public Contact getContact(String nom) {
        /*
        Contact contact1 = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM contacts c1 " +
                              "where c1.nom=?");) {
            pstmt.setString(1,nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nom1 = rs.getString(1);
                int telephone1 = rs.getInt(2);
                contact1 = new Contact(nom1,telephone1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contact1;

          */
        return null;
    }
}
