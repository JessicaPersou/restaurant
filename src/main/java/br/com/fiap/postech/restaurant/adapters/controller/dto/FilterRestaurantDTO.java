package br.com.fiap.postech.restaurant.adapters.controller.dto;

public class FilterRestaurantDTO {
    private String name;
    private String location;
    private String cuisine;

    public FilterRestaurantDTO() {
    }

    public FilterRestaurantDTO(String name, String location, String cuisine) {
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
