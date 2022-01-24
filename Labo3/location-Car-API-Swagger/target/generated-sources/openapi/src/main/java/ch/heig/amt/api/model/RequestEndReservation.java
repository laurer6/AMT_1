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
 * RequestEndReservation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
public class RequestEndReservation   {
  @JsonProperty("vehiculeId")
  private Integer vehiculeId;

  @JsonProperty("duration")
  private Integer duration;

  public RequestEndReservation vehiculeId(Integer vehiculeId) {
    this.vehiculeId = vehiculeId;
    return this;
  }

  /**
   * Get vehiculeId
   * @return vehiculeId
  */
  @Schema(name = "vehiculeId", defaultValue = "")


  public Integer getVehiculeId() {
    return vehiculeId;
  }

  public void setVehiculeId(Integer vehiculeId) {
    this.vehiculeId = vehiculeId;
  }

  public RequestEndReservation duration(Integer duration) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestEndReservation requestEndReservation = (RequestEndReservation) o;
    return Objects.equals(this.vehiculeId, requestEndReservation.vehiculeId) &&
        Objects.equals(this.duration, requestEndReservation.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehiculeId, duration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestEndReservation {\n");
    
    sb.append("    vehiculeId: ").append(toIndentedString(vehiculeId)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
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

