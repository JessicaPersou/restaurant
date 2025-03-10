package br.com.fiap.postech.restaurant.domain.entities;

import br.com.fiap.postech.restaurant.domain.enums.CuisineType;

import java.time.LocalTime;
import java.util.List;

public class Restaurant {
    private Long id;
    private String name;
    private String location;
    private CuisineType cuisine;
    private LocalTime openingHours;
    private int capacity;
    private List<Reservation> reservations;
    private List<Review> reviews;

    public boolean isValidOpeningHours() {
        return openingHours != null && openingHours.isAfter(LocalTime.of(6, 0));
    }

    public Restaurant() {

    }

    public Restaurant(String name, String location, CuisineType cuisineType,
                      LocalTime openingHours, int capacity, List<Reservation> reservations, List<Review> reviews) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome inválido");
        if (capacity <= 0) throw new IllegalArgumentException("Capacidade deve ser positiva");

        this.name = name;
        this.location = location;
        this.cuisine = cuisineType;
        this.openingHours = openingHours;
        this.capacity = capacity;
        this.reservations = reservations;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public CuisineType getCuisine() {
        return cuisine;
    }

    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCuisine(CuisineType cuisine) {
        this.cuisine = cuisine;
    }

    public void setOpeningHours(LocalTime openingHours) {
        this.openingHours = openingHours;
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