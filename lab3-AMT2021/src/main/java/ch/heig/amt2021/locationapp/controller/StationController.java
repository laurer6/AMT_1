package ch.heig.amt2021.locationapp.controller;

import ch.heig.amt2021.locationapp.model.request.StationDetailRequestModel;
import ch.heig.amt2021.locationapp.model.response.StationRest;
import ch.heig.amt2021.locationapp.service.IStationService;
import ch.heig.amt2021.locationapp.shared.dto.StationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stations") // http://localhost:8080/stations
public class StationController {

    @Autowired
    IStationService stationService;

    @GetMapping
    public String getStations() {
        return "get stations was called";
    }

    @PostMapping
    public StationRest createStation(@RequestBody StationDetailRequestModel stationDetailRequestModel) {
        StationRest returnValue = new StationRest();

        StationDto stationDto = new StationDto();
        BeanUtils.copyProperties(stationDetailRequestModel, stationDto);

        StationDto createStation = stationService.createStation(stationDto);

        BeanUtils.copyProperties(createStation, returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateStation() {
        return "update stations was called";
    }

    @DeleteMapping
    public String deleteStation() {
        return "delete stations was called";
    }

    @PatchMapping
    public String patchStation() {
        return "patch stations was called";
    }
}
