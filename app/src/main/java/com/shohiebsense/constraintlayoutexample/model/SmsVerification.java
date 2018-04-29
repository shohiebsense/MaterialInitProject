package com.shohiebsense.constraintlayoutexample.model;

/**
 * Created by Mukhamad Ikhsan on 2/20/2018.
 */

public class SmsVerification {
    private String code;
    private String message;

    public SmsVerification(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
