package ch.heig.amtlabo3.Entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "users_Gen")
    private int id;
    private String userName;
    private String userPassword;
    private BigDecimal solde;
    private boolean isAdmin;
    private boolean isBlocked;

    public UserEntity(){}

    public UserEntity(int id, String userName, String userPassword, BigDecimal solde, boolean isAdmin, boolean isBlocked) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.solde = solde;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}