package com.example.weatherapp1.UtilsPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.weatherapp1.R;
import com.example.weatherapp1.ViewModel.Hours;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
                return R.drawable.first;
            case "02":
                return R.drawable.second;
            case "03":
                return R.drawable.third;
            case "04":
                return R.drawable.fourth;
            case "09":
                return R.drawable.fifth;
            case "10":
                return R.drawable.sixth;
            case "11":
                return R.drawable.seventh;
            case "13":
                return R.drawable.eighth;
            case "50":
                return R.drawable.ninth;
            default:
                return R.drawable.qw;
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

}
