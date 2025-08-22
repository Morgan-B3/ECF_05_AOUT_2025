package org.example.environement.repository;

import org.example.environement.dto.observation.ObservationDtoResponse;
import org.example.environement.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation,Long> {
    public List<Observation> findObservationByLocationIsLike(String location);

    public List<Observation> findObservationBySpecieId(Long specieId);
}
