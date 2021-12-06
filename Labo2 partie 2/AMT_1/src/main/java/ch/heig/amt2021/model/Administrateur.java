package ch.heig.amt2021.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("ADMIN")
@Table(name = "administrateur")
public class Administrateur extends Utilisateur{

}