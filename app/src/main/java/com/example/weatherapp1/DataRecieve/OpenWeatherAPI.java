package com.example.weatherapp1.DataRecieve;

import com.example.weatherapp1.ViewModel.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherAPI {
    @GET("forecast")
    Call<WeatherData> getWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String appid);
}
