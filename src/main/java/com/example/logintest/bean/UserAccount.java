package com.example.logintest.bean;

import model.Trajet;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {

    private String userName;
    private String password;
    private List<String> roles;
    private int id;
    private boolean admin = false;
    private int trajet = 0;
    private float solde = 0;
    private Trajet tr;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, String... roles) {
        this.userName = userName;
        this.password = password;

        this.roles = new ArrayList<String>();
        if (roles != null) {
            for (String r : roles) {
                this.roles.add(r);
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public int getTrajet() { return trajet;    }

    public void setTrajet(int trajet) { this.trajet = trajet;    }

    public float getSolde() { return solde;    }

    public void setSolde(float solde) {  this.solde = solde;    }

    public boolean isAdmin() {        return admin;    }

    public void setAdmin(boolean admin) {        this.admin = admin;    }

    public int getId() { return id;    }

    public void setId(int id) { this.id = id;    }

    public Trajet getTr() { return tr;    }

    public void setTr(Trajet tr) { this.tr = tr;    }

}