package ch.heig.amt2021.locationapp.service;

import ch.heig.amt2021.locationapp.shared.dto.StationDto;

public interface IStationService {

    StationDto createStation(StationDto station);
}
