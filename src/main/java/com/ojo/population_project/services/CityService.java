package com.ojo.population_project.services;

import com.google.gson.Gson;
import com.ojo.population_project.models.CitiesResponse;
import com.ojo.population_project.models.CityInfo;
import com.ojo.population_project.models.CityPopulationData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface CityService {
    static List<CityInfo> getRatedCities() throws URISyntaxException, IOException, InterruptedException {

        Gson gson = new Gson();
//        String jsonRequest = gson.toJson(request);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://countriesnow.space/api/v0.1/countries/population/cities"))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .build();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        CitiesResponse citiesResponse = gson.fromJson(getResponse.body(), CitiesResponse.class);
        System.out.println(citiesResponse.getMsg());
        System.out.println(citiesResponse.getData().get(0).getCountry());
        List<CityPopulationData> data = citiesResponse.getData();
        List<CityPopulationData> ratedCities = data.stream()
                .filter(city -> {
                    String country = city.getCountry();
                    return country.equals("Ghana") || country.equals("Italy") || country.equals("New Zealand");
                })
                .collect(Collectors.toList());

        List<CityInfo> transformed = new ArrayList<>();
        for(CityPopulationData item: ratedCities){
            CityInfo cityInfo = new CityInfo();
            cityInfo.setCity(item.getCity());
            cityInfo.setCountry(item.getCountry());
            String latestPopulation = item.getPopulationCounts().get(0).getValue();
            try {
                cityInfo.setPopulation(Double.valueOf(latestPopulation));
            }catch (NumberFormatException e){
                cityInfo.setPopulation(0.0);
            }
            transformed.add(cityInfo);

        }

        return transformed;

    }

    default List<CityInfo> sortCitiesByPopulation(List<CityInfo> ratedCities) {
        List<CityInfo> sortedCities = ratedCities.stream()
                .sorted(Comparator.comparingDouble(CityInfo ::getPopulation))
                .collect(Collectors.toList());
        return sortedCities;




    }
    List<CityInfo> getTopCities(int numberOfCities) throws URISyntaxException, IOException, InterruptedException;

}


