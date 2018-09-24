package com.shohiebsense.fintechtemplate.model;

import com.google.gson.annotations.SerializedName;

public class CommonImage {

    String url;

    public CommonImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
