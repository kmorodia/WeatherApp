package com.example.weatherapp1.ViewModel;

import java.util.ArrayList;

public class WeatherData {

    private String cod;
    private String message;
    private City city;
    private Integer cnt;
    private ArrayList<Hours> list;

    public String getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public City getCity() {
        return city;
    }

    public ArrayList<Hours> getList() {
        return list;
    }
}