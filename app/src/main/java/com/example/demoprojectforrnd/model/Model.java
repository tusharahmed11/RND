package com.example.demoprojectforrnd.model;

public class Model {

    private String name;
    private String url;

    public Model(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
