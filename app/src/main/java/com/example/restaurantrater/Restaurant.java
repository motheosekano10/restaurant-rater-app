package com.example.restaurantrater;

public class Restaurant {
    int id;
    String name;
    String cuisine;
    double rating;

    public Restaurant(int id, String name, String cuisine, double rating) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
    }
}
