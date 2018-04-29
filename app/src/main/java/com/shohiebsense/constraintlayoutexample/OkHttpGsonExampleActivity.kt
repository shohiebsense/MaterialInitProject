package com.shohiebsense.constraintlayoutexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shohiebsense.constraintlayoutexample.model.ChuckNorris
import com.shohiebsense.constraintlayoutexample.network.chucknorris.GetChuckNorrisJokeTask

class OkHttpGsonExampleActivity : AppCompatActivity(), GetChuckNorrisJokeTask.GetChuckNorrisListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_gson_example)

        GetChuckNorrisJokeTask().getChuckNorrisJoke(this)
    }

    override fun onSuccess(chuckNorris: ChuckNorris?) {
        Log.e("shohiebsenseee ",chuckNorris!!.value )
    }
}
