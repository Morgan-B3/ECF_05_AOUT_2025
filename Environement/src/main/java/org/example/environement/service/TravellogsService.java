package org.example.environement.service;

import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.entity.Observation;
import org.example.environement.entity.Travellog;
import org.example.environement.exception.NotFoundException;
import org.example.environement.repository.ObservationRepository;
import org.example.environement.repository.TravellogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TravellogsService {
    private TravellogRepository travellogRepository;
    private ObservationRepository observationRepository;
    public TravellogsService (TravellogRepository travellogRepository, ObservationRepository observationRepository) {
        this.travellogRepository = travellogRepository;
        this.observationRepository = observationRepository;
    }

    public List<TravellogDtoResponse> get(int pageSize){
        return travellogRepository.findAll(PageRequest.of(0, pageSize)).stream().map(Travellog::entityToDto).collect(Collectors.toList());
    }

    public TravellogDtoStat getStat(long id){
        Observation observation = observationRepository.findById(id).orElseThrow(NotFoundException::new);
        TravellogDtoStat travellogDtoStat = new TravellogDtoStat();
        for (Travellog travellog : observation.getTravellogs()){
            travellogDtoStat.addTotalEmissionsKg(travellog.getEstimatedCo2Kg());
            travellogDtoStat.addMode(travellog.getMode().toString(), travellog.getDistanceKm());
            travellogDtoStat.addTotalDistanceKm(travellog.getDistanceKm());
        }
        return travellogDtoStat;
    }

    public TravellogDtoResponse createTravellog (TravellogDtoReceive travellogDtoReceive){
        return travellogRepository.save(travellogDtoReceive.dtoToEntity()).entityToDto();
    }

    public Map<String, TravellogDtoStat> getStatForUserLastMonth(String name){
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);
        Map<String, TravellogDtoStat> travellogsMap = new HashMap<String, TravellogDtoStat>();
        List<Travellog> travellogsResult = travellogRepository.findTravellogByUserForLastMonth(name, lastMonth);
        List<Long> observationIds = new ArrayList<>();
        for (Travellog travellog : travellogsResult) {
            TravellogDtoStat travellogDtoStat = new  TravellogDtoStat();
            String day = travellog.getObservation().getObservationDate().toString();
            if(observationIds.contains(travellog.getObservation().getId())){
                travellogDtoStat = travellogsMap.get(travellog.getObservation().getObservationDate().toString());
            } else {
                observationIds.add(travellog.getObservation().getId());
            }
            travellogDtoStat.addTotalDistanceKm(travellog.getDistanceKm());
            travellogDtoStat.addMode(travellog.getMode().toString(), travellog.getDistanceKm());
            travellogDtoStat.addTotalEmissionsKg(travellog.getEstimatedCo2Kg());
            travellogsMap.put(day, travellogDtoStat);
        }
        System.out.println(observationIds);

        return travellogsMap;
    }
}
