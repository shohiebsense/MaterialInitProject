package com.shohiebsense.fintechtemplate.model;

public class CommonImageRequest {


    int width;
    int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CommonImageRequest(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
