package com.clinic.app.model;

public class UserToken {
    private String accessToken;
    private String expiresIn;

    public UserToken() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
