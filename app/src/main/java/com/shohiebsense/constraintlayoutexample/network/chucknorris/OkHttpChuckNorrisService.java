package com.shohiebsense.constraintlayoutexample.network.chucknorris;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shohiebsense.constraintlayoutexample.model.ChuckNorris;
import com.shohiebsense.constraintlayoutexample.model.ChuckNorrisRequest;

import java.io.BufferedReader;
import java.io.Reader;

import okhttp3.Response;

public class OkHttpChuckNorrisService {



    public ChuckNorris getJoke(ChuckNorrisRequest chuckNorrisRequest){
        ChuckNorris chuckNorris;
        Response response = new OkHttpChuckNorrisResponseService().getJoke(chuckNorrisRequest);
        if(response == null){
            return null;
        }
        Reader in = response.body().charStream();
        BufferedReader reader = new BufferedReader(in);
        // chuckNorris = new Gson().fromJson(reader,ChuckNorris.class);
        chuckNorris = new Gson().fromJson(reader, new TypeToken<ChuckNorris>(){}.getType());
        return chuckNorris;
    }
}
