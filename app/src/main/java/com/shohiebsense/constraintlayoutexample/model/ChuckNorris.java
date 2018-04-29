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

    public ChuckNorris() {
    }

    public ChuckNorris(String iconUrl, String id, String url, String value, ArrayList<String> category) {
        this.iconUrl = iconUrl;
        this.id = id;
        this.url = url;
        this.value = value;
        this.category = category;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getValue() {
        return value;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }
}
