package com.example.consumirrest.entity;

public class Category {
    private int id;
    private String name;
    private String typeImg;

    // Constructor
    public Category(int id, String name, String typeImg) {
        this.id = id;
        this.name = name;
        this.typeImg = typeImg;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }
}
