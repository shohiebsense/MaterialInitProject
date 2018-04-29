package com.shohiebsense.constraintlayoutexample.network.getimages;

import android.util.Log;

import com.shohiebsense.constraintlayoutexample.util.Constants;
import com.shohiebsense.constraintlayoutexample.util.Contacts;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpImageResponseService {


    public Response getImageResource(int width, int height){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(this.getClass().getName(),message);
            }
        });


        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        Request request = new Request.Builder().url(Constants.PLACEKITTEN_URL+"/"+width+"/"+height).build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
