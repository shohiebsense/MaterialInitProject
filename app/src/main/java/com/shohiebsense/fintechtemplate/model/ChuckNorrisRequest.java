package com.shohiebsense.fintechtemplate.model;

import com.google.gson.annotations.SerializedName;

public class ChuckNorrisRequest {

    @SerializedName("category")
    private String category;



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ChuckNorrisRequest() {
    }

    public ChuckNorrisRequest(String category) {
        this.category = category;
    }
}
