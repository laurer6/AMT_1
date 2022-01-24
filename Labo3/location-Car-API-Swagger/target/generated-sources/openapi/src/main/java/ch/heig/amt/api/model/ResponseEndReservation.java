package ch.heig.amt.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * ResponseEndReservation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
public class ResponseEndReservation   {
  @JsonProperty("matriculeVehicle")
  private String matriculeVehicle;

  @JsonProperty("duration")
  private Integer duration;

  @JsonProperty("price")
  private BigDecimal price;

  public ResponseEndReservation matriculeVehicle(String matriculeVehicle) {
    this.matriculeVehicle = matriculeVehicle;
    return this;
  }

  /**
   * Get matriculeVehicle
   * @return matriculeVehicle
  */
  @Schema(name = "matriculeVehicle", defaultValue = "")


  public String getMatriculeVehicle() {
    return matriculeVehicle;
  }

  public void setMatriculeVehicle(String matriculeVehicle) {
    this.matriculeVehicle = matriculeVehicle;
  }

  public ResponseEndReservation duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Get duration
   * @return duration
  */
  @Schema(name = "duration", defaultValue = "")


  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public ResponseEndReservation price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @Schema(name = "price", defaultValue = "")

  @Valid

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseEndReservation responseEndReservation = (ResponseEndReservation) o;
    return Objects.equals(this.matriculeVehicle, responseEndReservation.matriculeVehicle) &&
        Objects.equals(this.duration, responseEndReservation.duration) &&
        Objects.equals(this.price, responseEndReservation.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matriculeVehicle, duration, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseEndReservation {\n");
    
    sb.append("    matriculeVehicle: ").append(toIndentedString(matriculeVehicle)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

