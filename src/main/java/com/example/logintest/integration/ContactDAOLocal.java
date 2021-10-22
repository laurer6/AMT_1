package com.example.logintest.integration;

import model.Contact;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ContactDAOLocal {
    List<Contact> getContacts();
    void add(Contact contact);
    Contact getContact(String nom);
}
