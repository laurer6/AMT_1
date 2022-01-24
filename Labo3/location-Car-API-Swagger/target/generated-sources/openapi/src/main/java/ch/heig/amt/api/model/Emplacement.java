package ch.heig.amt.api.model;

import java.net.URI;
import java.util.Objects;
import ch.heig.amt.api.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

/**
 * Emplacement
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
public class Emplacement   {
  @JsonProperty("numero")
  private Integer numero;

  /**
   * Gets or Sets statut
   */
  public enum StatutEnum {
    LIBRE("libre"),
    
    OCCUP_("occupé"),
    
    R_SERV_("réservé");

    private String value;

    StatutEnum(String value) {
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
    public static StatutEnum fromValue(String value) {
      for (StatutEnum b : StatutEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("statut")
  private StatutEnum statut;

  @JsonProperty("vehicle")
  private Vehicle vehicle;

  public Emplacement numero(Integer numero) {
    this.numero = numero;
    return this;
  }

  /**
   * Get numero
   * @return numero
  */
  @Schema(name = "numero", defaultValue = "")


  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public Emplacement statut(StatutEnum statut) {
    this.statut = statut;
    return this;
  }

  /**
   * Get statut
   * @return statut
  */
  @Schema(name = "statut", defaultValue = "")


  public StatutEnum getStatut() {
    return statut;
  }

  public void setStatut(StatutEnum statut) {
    this.statut = statut;
  }

  public Emplacement vehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
    return this;
  }

  /**
   * Get vehicle
   * @return vehicle
  */
  @Schema(name = "vehicle", defaultValue = "")

  @Valid

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Emplacement emplacement = (Emplacement) o;
    return Objects.equals(this.numero, emplacement.numero) &&
        Objects.equals(this.statut, emplacement.statut) &&
        Objects.equals(this.vehicle, emplacement.vehicle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numero, statut, vehicle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Emplacement {\n");
    
    sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
    sb.append("    statut: ").append(toIndentedString(statut)).append("\n");
    sb.append("    vehicle: ").append(toIndentedString(vehicle)).append("\n");
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

