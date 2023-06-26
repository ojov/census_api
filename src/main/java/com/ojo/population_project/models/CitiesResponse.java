package com.ojo.population_project.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CitiesResponse {
    private boolean error;
    private String msg;
    private List<CityPopulationData> data;

    @Override
    public String toString() {
        return "CitiesResponse{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
