package com.shohiebsense.constraintlayoutexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChuckNorris {
    @SerializedName("icon_url")
    private String iconUrl;

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    @SerializedName("value")
    private String value;

    @SerializedName("category")
    private ArrayList<String> category;

}
