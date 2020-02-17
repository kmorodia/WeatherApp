package com.example.weatherapp1.ViewModel;

import java.util.ArrayList;

public class Hours {
    private String dt;
    private String dt_txt;
    private Main main;
    private ArrayList<Weather> weather;


    public String getDt() {
        return dt;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
}
