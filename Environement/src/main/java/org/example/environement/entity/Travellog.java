package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.entity.enums.TravelMode;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Travellog {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, name = "distance_km")
    private Double distanceKm;
    private TravelMode mode;
    @Column(name = "estimated_co2_kg", nullable = false)
    private Double estimatedCo2Kg;

    @ManyToOne
    @JoinColumn(name = "observation_id")
    private Observation observation;

    public TravellogDtoResponse entityToDto (){
        return TravellogDtoResponse.builder()
                .id(this.getId())
                .estimatedCo2Kg(this.getEstimatedCo2Kg())
                .distanceKm(this.getDistanceKm())
                .mode(this.getMode().toString())
                .build();
    }

    public void calculateCO2(){
        Double emissionFactor = 0d;
        switch (this.mode){
            case CAR -> emissionFactor = 0.22;
            case BUS -> emissionFactor = 0.11;
            case TRAIN -> emissionFactor = 0.03;
            case PLANE ->  emissionFactor = 0.259;
        }

        Double estimatedCo2Kg = this.getDistanceKm() * emissionFactor;

        this.setEstimatedCo2Kg(estimatedCo2Kg);
    }

}
