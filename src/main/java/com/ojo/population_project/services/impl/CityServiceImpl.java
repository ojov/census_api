package com.ojo.population_project.services.impl;

import com.ojo.population_project.models.CityInfo;
import com.ojo.population_project.services.CityService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

public class CityServiceImpl implements CityService {
    @Override
    public List<CityInfo> getTopCities(int numberOfCities) throws URISyntaxException, IOException, InterruptedException {
        List<CityInfo> cities =
        sortCitiesByPopulation(CityService.getRatedCities());


        Collections.reverse(cities);
        List<CityInfo> topCities = cities.subList(0, Math.min(cities.size(), numberOfCities));
        System.out.println(topCities.get(0).getCity() + topCities.get(1).getCountry());
        return topCities;




    }
}
