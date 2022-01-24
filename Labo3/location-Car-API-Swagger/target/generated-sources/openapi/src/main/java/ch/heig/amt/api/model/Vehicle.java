package ch.heig.amt.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

/**
 * Vehicle
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
public class Vehicle   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("matricule")
  private String matricule;

  /**
   * Gets or Sets categorie
   */
  public enum CategorieEnum {
    BERLINE("Berline"),
    
    MOTO("Moto"),
    
    FOURGON("Fourgon");

    private String value;

    CategorieEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CategorieEnum fromValue(String value) {
      for (CategorieEnum b : CategorieEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("categorie")
  private CategorieEnum categorie;

  @JsonProperty("minPrice")
  private BigDecimal minPrice;

  public Vehicle id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Schema(name = "id", defaultValue = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Vehicle matricule(String matricule) {
    this.matricule = matricule;
    return this;
  }

  /**
   * Get matricule
   * @return matricule
  */
  @Schema(name = "matricule", defaultValue = "")


  public String getMatricule() {
    return matricule;
  }

  public void setMatricule(String matricule) {
    this.matricule = matricule;
  }

  public Vehicle categorie(CategorieEnum categorie) {
    this.categorie = categorie;
    return this;
  }

  /**
   * Get categorie
   * @return categorie
  */
  @Schema(name = "categorie", defaultValue = "")


  public CategorieEnum getCategorie() {
    return categorie;
  }

  public void setCategorie(CategorieEnum categorie) {
    this.categorie = categorie;
  }

  public Vehicle minPrice(BigDecimal minPrice) {
    this.minPrice = minPrice;
    return this;
  }

  /**
   * Get minPrice
   * @return minPrice
  */
  @Schema(name = "minPrice", defaultValue = "")

  @Valid

  public BigDecimal getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(BigDecimal minPrice) {
    this.minPrice = minPrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(this.id, vehicle.id) &&
        Objects.equals(this.matricule, vehicle.matricule) &&
        Objects.equals(this.categorie, vehicle.categorie) &&
        Objects.equals(this.minPrice, vehicle.minPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, matricule, categorie, minPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehicle {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    matricule: ").append(toIndentedString(matricule)).append("\n");
    sb.append("    categorie: ").append(toIndentedString(categorie)).append("\n");
    sb.append("    minPrice: ").append(toIndentedString(minPrice)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

