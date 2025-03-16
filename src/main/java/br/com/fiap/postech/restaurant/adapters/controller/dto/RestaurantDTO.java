package br.com.fiap.postech.restaurant.adapters.controller.dto;

import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Review;
import br.com.fiap.postech.restaurant.domain.enums.CuisineType;
import br.com.fiap.postech.restaurant.domain.valueobjects.OpeningHours;

import java.time.LocalTime;
import java.util.List;

public class RestaurantDTO {
    private Long id;
    private String name;
    private String location;
    private CuisineType cuisine;
    private List<OpeningHours> openingHours;
    private int capacity;
    private List<Reservation> reservations;
    private List<Review> reviews;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, String location, CuisineType cuisine, List<OpeningHours> openingHours, int capacity, List<Reservation> reservations, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.openingHours = openingHours;
        this.capacity = capacity;
        this.reservations = reservations;
        this.reviews = reviews;
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

    public List<OpeningHours> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<OpeningHours> openingHours) {
        this.openingHours = openingHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}