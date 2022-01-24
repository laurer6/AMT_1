package ch.heig.amt.api.model;

import java.net.URI;
import java.util.Objects;
import ch.heig.amt.api.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

/**
 * ResponseReservation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
public class ResponseReservation   {
  @JsonProperty("vehicle")
  private Vehicle vehicle;

  @JsonProperty("from")
  private String from;

  @JsonProperty("to")
  private String to;

  public ResponseReservation vehicle(Vehicle vehicle) {
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

  public ResponseReservation from(String from) {
    this.from = from;
    return this;
  }

  /**
   * Get from
   * @return from
  */
  @Schema(name = "from", defaultValue = "")


  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public ResponseReservation to(String to) {
    this.to = to;
    return this;
  }

  /**
   * Get to
   * @return to
  */
  @Schema(name = "to", defaultValue = "")


  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseReservation responseReservation = (ResponseReservation) o;
    return Objects.equals(this.vehicle, responseReservation.vehicle) &&
        Objects.equals(this.from, responseReservation.from) &&
        Objects.equals(this.to, responseReservation.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicle, from, to);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseReservation {\n");
    
    sb.append("    vehicle: ").append(toIndentedString(vehicle)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
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

