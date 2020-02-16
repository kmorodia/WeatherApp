package com.example.weatherapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weatherapp1.DataRecieve.OpenWeatherAPI;
import com.example.weatherapp1.ViewModel.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("log2", "-------------- here2 ----------------");
        //ListView listView = findViewById(R.id.listView);
        //listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                //new String[] {"Temp", "Humidity", "Sunshine", "Hazy", "Windy", "Cloudy"}));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherAPI openWeatherAPI = retrofit.create(OpenWeatherAPI.class);

        Call<WeatherData> call = openWeatherAPI.getWeatherData("13","78", "c5b93ca42e48d881d625d68b68412037");

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.code());
                    return;
                }
                else {
                    WeatherData weatherData = response.body();

                    String content = "";
                    content += "cod" + weatherData.getCod();
                    content += "message" + weatherData.getMessage();
                    content += "cnt" + weatherData.getCnt();
                    content += "city" + weatherData.getCity().getString();
                    content += "hour0" + weatherData.getList().get(0).getDt_txt();


                    Log.d(TAG, "onSuccessfulResponse: " + content);
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}
