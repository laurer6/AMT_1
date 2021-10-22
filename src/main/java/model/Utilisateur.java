package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Utilisateur implements Serializable{

    private int id;
    private String login;
    private String password;

    private List<String> roles;

    public Utilisateur() {

    }

    public Utilisateur(int id, String login, String password) {
        this.id = id;
        this.password = password;
        this.login = login;
    }

    public void setId(int id){this.id = id;}

    public int getId() { return id;  }

    public void setLogin(String login) { this.login = login;  }

    public String getLogin() { return login; }

    public String getPassword() { return password;    }

    public void setPassword(String password) { this.password = password;    }

    public List<String> getRoles() {  return roles;    }

    public void setRoles(List<String> roles) { this.roles = roles;    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur utilisateur = (Utilisateur) o;
        return utilisateur.equals(utilisateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id='" + id + '\'' +
                ", login =" + login +
                '}';
    }
}
