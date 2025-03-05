package br.com.fiap.postech.restaurant_reservation.infra.controller.dto;

public class FilterRestaurant {
    private String name;
    private String location;
    private String cuisine;

    public FilterRestaurant(){}

    public FilterRestaurant(String name, String location, String cuisine) {
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
