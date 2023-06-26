package com.ojo.population_project.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityPopulationData {
    private String city;
    private String country;
    private List<PopulationData> populationCounts;
}
