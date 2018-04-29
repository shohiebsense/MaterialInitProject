package com.shohiebsense.constraintlayoutexample.network.chucknorris;

import android.util.Log;

import com.google.gson.Gson;
import com.shohiebsense.constraintlayoutexample.model.ChuckNorrisRequest;
import com.shohiebsense.constraintlayoutexample.util.AppUtil;
import com.shohiebsense.constraintlayoutexample.util.Constants;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpChuckNorrisResponseService  {


    public Response getJoke(ChuckNorrisRequest chuckNorrisRequest){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("shohiebsenseee ",message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        HttpUrl.Builder httpBuilder = HttpUrl.parse(Constants.CHUCKNORRIS_URL).newBuilder();
        httpBuilder.addQueryParameter("category",chuckNorrisRequest.getCategory());
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        Request request = new Request.Builder().url(httpBuilder.build())
                .build();

        //for POST Mode, and the structure is Json object not plain String parameter
        RequestBody body =new FormBody.Builder()
                .add("category",new Gson().toJson(chuckNorrisRequest))
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
