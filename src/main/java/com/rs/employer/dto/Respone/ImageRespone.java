package com.rs.employer.dto.Respone;

public class ImageRespone {
    private String base64string;
    private String username;

    public ImageRespone() {
    }

    public String getBase64string() {
        return base64string;
    }

    public void setBase64string(String base64string) {
        this.base64string = base64string;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ImageRespone(String base64string, String username) {
        this.base64string = base64string;
        this.username = username;
    }
}
