package br.com.fiap.postech.restaurant_reservation.infra.controller.dto;

import br.com.fiap.postech.restaurant_reservation.domain.enums.CuisineType;

import java.time.LocalTime;

public class RestaurantDTO {
    private Long id;
    private String name;
    private String location;
    private CuisineType cuisine;
    private LocalTime openingHours;
    private int capacity;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, String location, CuisineType cuisine, LocalTime openingHours, int capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.openingHours = openingHours;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CuisineType getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineType cuisine) {
        this.cuisine = cuisine;
    }

    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(LocalTime openingHours) {
        this.openingHours = openingHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}