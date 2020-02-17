package com.example.weatherapp1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.TextView;
import android.view.LayoutInflater;

import com.example.weatherapp1.UtilsPackage.UtilFuns;
import com.example.weatherapp1.ViewModel.Hours;

//import com.example.weatherapp1.UtilsPackage.Utils;

import java.util.ArrayList;

public class WeatherAdapter extends ArrayAdapter<Hours> {
    public WeatherAdapter(@NonNull Context context, ArrayList<Hours> weatherArrayList) {
        super(context, 0, weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Hours hour = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView dateTextView = convertView.findViewById(R.id.tvDate);
        TextView minTextView = convertView.findViewById(R.id.tvLowTemperature);
        TextView maxTextView = convertView.findViewById(R.id.tvHighTemperature);
        TextView descriptionTextView = convertView.findViewById(R.id.tvDescription);

        dateTextView.setText(hour.getDt_txt());
        minTextView.setText(UtilFuns.kelvin2Celsius(hour.getMain().getTemp_min()));
        maxTextView.setText(UtilFuns.kelvin2Celsius(hour.getMain().getTemp_max()));
        descriptionTextView.setText(hour.getWeather().get(0).getMain());

        return convertView;
    }
}
