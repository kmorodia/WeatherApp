package com.example.weatherapp1.UtilsPackage;

import com.example.weatherapp1.ViewModel.Hours;

import java.util.ArrayList;

public class UtilFuns {

    UtilFuns(){

    }

    public static String kelvin2Celsius(String K){
        String ans = String.valueOf((Float.valueOf(K).intValue()) - 273);
        return ans+"°C";
    }

    public static ArrayList<Hours> returnDays(ArrayList<Hours> forty){
        for(int i=39;i>0;i--){
            if(i%8 != 0){
                forty.remove(i);
            }
        }
        return forty;
    }
}
