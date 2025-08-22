package org.example.environement.config;

import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.entity.Observation;
import org.example.environement.entity.Specie;
import org.example.environement.entity.Travellog;
import org.example.environement.entity.enums.Category;
import org.example.environement.entity.enums.TravelMode;
import org.example.environement.repository.ObservationRepository;
import org.example.environement.repository.SpecieRepository;
import org.example.environement.repository.TravellogRepository;
import org.example.environement.service.SpecieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(SpecieRepository specieRepository, ObservationRepository observationRepository, TravellogRepository travellogRepository) {
        return args -> {
//            Random random = new Random();
//            if(specieRepository.count()==0){
//                IntStream.range(0,20).forEach(i -> {
//                    Specie specie = new Specie();
//                    specie.setCommonName("Nom commun "+i);
//                    specie.setScientificName("Nom scientifique "+i);
//                    specie.setCategory(Category.values()[random.nextInt(Category.values().length)]);
//                    specieRepository.save(specie);
//                });
//            }
//            if(travellogRepository.count()<100){
//                IntStream.range(0,20).forEach(i -> {
//                    TravellogDtoReceive travellog = new TravellogDtoReceive();
//                    TravelMode mode = TravelMode.values()[random.nextInt(TravelMode.values().length)];
//                    travellog.setMode(mode.toString());
//                    travellog.setDistanceKm(random.nextInt(1000));
//                    travellogRepository.save(travellog.dtoToEntity());
//                });
//            }
//            if(observationRepository.count()<100){
//                List<Travellog> allTravellogs = travellogRepository.findAll();
//                List<Specie> allSpecies = specieRepository.findAll();
//                long minDay = LocalDate.of(2025, 6, 22).toEpochDay();
//                long maxDay = LocalDate.of(2025, 8, 22).toEpochDay();
//                IntStream.range(0,20).forEach(i -> {
//                    Observation observation = new Observation();
//                    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
//                    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
//
//                    observation.setComment("Commentaire "+i);
//                    observation.setObservationDate(randomDate);
//                    observation.setLongitude(random.nextDouble());
//                    observation.setLatitude(random.nextDouble());
//                    observation.setLocation("Ville "+i);
//                    observation.setObserverName("Nom "+i);
//                    Specie randomSpecie = allSpecies.get(random.nextInt(allSpecies.size()));
//                    observation.setSpecie(randomSpecie);
//
//                    int numberOfTravellogs = 1 + random.nextInt(5);
//                    Collections.shuffle(allTravellogs);
//
//                    allTravellogs.stream()
//                            .limit(numberOfTravellogs)
//                            .forEach(observation::addTravellog);
//
//                    observationRepository.save(observation);
//                });
//            }
        };
    }
}
