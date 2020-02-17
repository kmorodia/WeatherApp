package com.example.weatherapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp1.DataRecieve.OpenWeatherAPI;
import com.example.weatherapp1.UtilsPackage.UtilFuns;
import com.example.weatherapp1.ViewModel.WeatherData;

import java.lang.annotation.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LocationListener {

    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;

    private static final String TAG = "MainActivity";
    private TextView temperature;
    private TextView weatherDescription;
    private TextView cityTextView;
    private TextView countryTextView;

    private Context contexthere;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    {
        contexthere = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperature = findViewById(R.id.temperatureDisplay);
        weatherDescription = findViewById(R.id.weatherDescription);
        cityTextView = findViewById(R.id.cityTextView);
        countryTextView = findViewById(R.id.countryTextView);


        ListView listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherAPI openWeatherAPI = retrofit.create(OpenWeatherAPI.class);

        getLocation();
        //locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        //Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        Log.d(TAG, "latlnog: "+ latitude + longitude);

        Call<WeatherData> call = openWeatherAPI.getWeatherData(String.valueOf((int) latitude), String.valueOf((int) longitude), "c5b93ca42e48d881d625d68b68412037");

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                } else {
                    WeatherData weatherData = response.body();

                    temperature.setText(UtilFuns.kelvin2Celsius(weatherData.getList().get(0).getMain().getTemp()));
                    weatherDescription.setText(weatherData.getList().get(0).getWeather().get(0).getMain());
                    cityTextView.setText(weatherData.getCity().getName());
                    countryTextView.setText(weatherData.getCity().getCountry());


                    WeatherAdapter weatherAdapter = new WeatherAdapter(contexthere, UtilFuns.returnDays(weatherData.getList()));
                    listView.setAdapter(weatherAdapter);

                    Log.d(TAG, "onSuccessfulResponse: success!");
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public static boolean isLocationEnabled(Context context) {
        //...............
        return true;
    }

    @TargetApi(23)
    protected void getLocation() {
        if (isLocationEnabled(MainActivity.this)) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //You can still do this if you like, you might get lucky:
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                Log.e("TAG", "GPS is on");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Toast.makeText(MainActivity.this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
            } else {
                //This is what you need:
                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
            }
        }
        else
        {
            Log.d(TAG, "__permBefore");
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onCreate: userperms");
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                //return;
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "StillNoUserperms");
                return;
            }
            Log.d(TAG, "__permAfter");
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //Hey, a non null location! Sweet!

        //remove location callback:
        //locationManager.removeUpdates(this);

        //open the map:
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}


