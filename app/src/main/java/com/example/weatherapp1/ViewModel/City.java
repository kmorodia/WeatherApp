package com.example.weatherapp1.ViewModel;

public class City {
    private String id;
    private String name;
    private String country;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getString(){
        return id + name + country;
    }
}
