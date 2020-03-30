package com.example.weatherapp1.UtilsPackage;

import com.example.weatherapp1.R;
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

    public static int getImageResource(String s){
        switch (s.substring(0,2)){
            case "01":
                return R.drawable.qw;
            case "02":
                return R.drawable.qw;
            case "03":
                return R.drawable.qw;
            case "04":
                return R.drawable.qw;
            case "09":
                return R.drawable.qw;
            case "10":
                return R.drawable.qw;
            case "11":
                return R.drawable.qw;
            case "13":
                return R.drawable.qw;
            case "50":
                return R.drawable.qw;
            default:
                return R.drawable.qw;
        }
    }

}
