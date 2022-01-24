package ch.heig.amt.api.model;

import java.net.URI;
import java.util.Objects;
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
 * RequestReservation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
public class RequestReservation   {
  @JsonProperty("vehicleId")
  private Integer vehicleId;

  @JsonProperty("stationIdDestination")
  private Integer stationIdDestination;

  public RequestReservation vehicleId(Integer vehicleId) {
    this.vehicleId = vehicleId;
    return this;
  }

  /**
   * Get vehicleId
   * @return vehicleId
  */
  @Schema(name = "vehicleId", defaultValue = "")


  public Integer getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Integer vehicleId) {
    this.vehicleId = vehicleId;
  }

  public RequestReservation stationIdDestination(Integer stationIdDestination) {
    this.stationIdDestination = stationIdDestination;
    return this;
  }

  /**
   * Get stationIdDestination
   * @return stationIdDestination
  */
  @Schema(name = "stationIdDestination", defaultValue = "")


  public Integer getStationIdDestination() {
    return stationIdDestination;
  }

  public void setStationIdDestination(Integer stationIdDestination) {
    this.stationIdDestination = stationIdDestination;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestReservation requestReservation = (RequestReservation) o;
    return Objects.equals(this.vehicleId, requestReservation.vehicleId) &&
        Objects.equals(this.stationIdDestination, requestReservation.stationIdDestination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicleId, stationIdDestination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestReservation {\n");
    
    sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
    sb.append("    stationIdDestination: ").append(toIndentedString(stationIdDestination)).append("\n");
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

