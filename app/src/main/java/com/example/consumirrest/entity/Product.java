package com.example.consumirrest.entity;

public class Product {
    private int id;
    private String title;
    private double price;
    private String description;
    private Category category;


    // Constructor
    public Product(int id, String title, double price, String description, Category category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;

    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
