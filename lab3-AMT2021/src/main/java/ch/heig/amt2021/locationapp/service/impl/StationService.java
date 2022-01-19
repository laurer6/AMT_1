package ch.heig.amt2021.locationapp.service.impl;

import ch.heig.amt2021.locationapp.StationRepository;
import ch.heig.amt2021.locationapp.io.entity.Station;
import ch.heig.amt2021.locationapp.service.IStationService;
import ch.heig.amt2021.locationapp.shared.Utils;
import ch.heig.amt2021.locationapp.shared.dto.StationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService implements IStationService {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    Utils utils;

    @Override
    public StationDto createStation(StationDto station) {

        if(stationRepository.findByAddress(station.getAddress()) != null) throw new RuntimeException("Record already exists");

        Station stationEntity = new Station();
        BeanUtils.copyProperties(station, stationEntity);

        stationEntity.setStationId(utils.generateStationId(30));

        Station storedStationDetails = stationRepository.save(stationEntity);

        StationDto returnValue = new StationDto();
        BeanUtils.copyProperties(storedStationDetails, returnValue);

        return returnValue;
    }
}
