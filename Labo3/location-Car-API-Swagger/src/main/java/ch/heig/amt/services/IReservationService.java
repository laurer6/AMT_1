package ch.heig.amt.services;


import ch.heig.amt.api.model.ResponseReservation;

public interface IReservationService {

    /**
     * Permet de réserver un véhicule. L'utilisateur qui fait la réservation est inféré
     * en fonction du token.
     * @param vehicleId
     * @param stationId
     */
    ResponseReservation makeReservation(Integer vehicleId, Integer stationId);

    /**
     * Permet de rendre un véhicule qui a été réservé. L'utilisateur qui rend le véhicule
     * est inféré en fonction du token
     * @param reservationId
     */
    void endReservation(Integer reservationId);
}
