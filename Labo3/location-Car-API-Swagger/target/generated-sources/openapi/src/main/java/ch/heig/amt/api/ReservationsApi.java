/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.heig.amt.api;

import ch.heig.amt.api.model.RequestEndReservation;
import ch.heig.amt.api.model.RequestReservation;
import ch.heig.amt.api.model.ResponseEndReservation;
import ch.heig.amt.api.model.ResponseReservation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-24T18:26:34.074403300+01:00[Europe/Berlin]")
@Validated
@Tag(name = "reservations", description = "the reservations API")
public interface ReservationsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /reservations/end : Rendre un véhicule
     * Permettre de rendre un véhicule pour mettre fin à une réservation
     *
     * @param requestEndReservation  (optional)
     * @return Confirmed (status code 200)
     */
    @Operation(summary = "Rendre un véhicule", tags={ "Réservations", }, responses = {  @ApiResponse(responseCode = "200", description = "Confirmed", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ResponseEndReservation.class))) },security = {
        @SecurityRequirement(name = "bearerAuth") } )
        @RequestMapping(
        method = RequestMethod.POST,
        value = "/reservations/end",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ResponseEndReservation> endReservation(

@Parameter(name = "" )   @Valid @RequestBody(required = false) RequestEndReservation requestEndReservation) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"duration\" : 0, \"price\" : 6.027456183070403, \"matriculeVehicle\" : \"matriculeVehicle\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /reservations/start : Faire un réservation
     * Permet de faire la réservation d&#39;un vehicule
     *
     * @param requestReservation  (optional)
     * @return Confirmed (status code 201)
     */
    @Operation(summary = "Faire un réservation", tags={ "Réservations", }, responses = {  @ApiResponse(responseCode = "201", description = "Confirmed", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ResponseReservation.class))) },security = {
        @SecurityRequirement(name = "bearerAuth") } )
        @RequestMapping(
        method = RequestMethod.POST,
        value = "/reservations/start",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ResponseReservation> reserveVehicle(

@Parameter(name = "" )   @Valid @RequestBody(required = false) RequestReservation requestReservation) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"from\" : \"from\", \"to\" : \"to\", \"vehicle\" : { \"categorie\" : \"Berline\", \"matricule\" : \"matricule\", \"minPrice\" : 1.4658129805029452, \"id\" : 6 } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
