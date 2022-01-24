package ch.heig.amt.api.model;

import java.net.URI;
import java.util.Objects;
import ch.heig.amt.api.model.Emplacement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

/**
 * Station
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
public class Station   {
  @JsonProperty("address")
  private String address;

  @JsonProperty("emplacements")
  @Valid
  private List<Emplacement> emplacements = null;

  public Station address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @Schema(name = "address", defaultValue = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Station emplacements(List<Emplacement> emplacements) {
    this.emplacements = emplacements;
    return this;
  }

  public Station addEmplacementsItem(Emplacement emplacementsItem) {
    if (this.emplacements == null) {
      this.emplacements = new ArrayList<>();
    }
    this.emplacements.add(emplacementsItem);
    return this;
  }

  /**
   * Get emplacements
   * @return emplacements
  */
  @Schema(name = "emplacements", defaultValue = "")

  @Valid

  public List<Emplacement> getEmplacements() {
    return emplacements;
  }

  public void setEmplacements(List<Emplacement> emplacements) {
    this.emplacements = emplacements;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Station station = (Station) o;
    return Objects.equals(this.address, station.address) &&
        Objects.equals(this.emplacements, station.emplacements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, emplacements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Station {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    emplacements: ").append(toIndentedString(emplacements)).append("\n");
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

