package br.com.fiap.postech.restaurant.domain.entities;

import br.com.fiap.postech.restaurant.domain.enums.CuisineType;
import br.com.fiap.postech.restaurant.domain.valueobjects.OpeningHours;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private Long id;
    private String name;
    private String location;
    private CuisineType cuisine;
    private List<OpeningHours> openingHours = new ArrayList<>();
    private int capacity;
    private List<Reservation> reservations = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public Restaurant(long l, String s, int i, Object o) {
    }

    // Metodo para verificar se está aberto em determinado horário
    public boolean isOpenAt(LocalDateTime dateTime) {
        if (dateTime == null || openingHours == null || openingHours.isEmpty()) {
            return false;
        }

        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        LocalTime time = dateTime.toLocalTime();

        return openingHours.stream()
                .filter(oh -> oh.getDayOfWeek() == dayOfWeek)
                .anyMatch(oh -> oh.isTimeWithinBusinessHours(time));
    }

    // Corrigido o metodo para adicionar horário de funcionamento
    public void addOpeningHours(OpeningHours hours) {
        if (hours != null) {
            this.openingHours.add(hours);
        }
    }

    // Corrigido o metodo para remover horário de funcionamento
    public void removeOpeningHours(OpeningHours hours) {
        if (hours != null) {
            this.openingHours.remove(hours);
        }
    }

    public Restaurant() {
    }

    public Restaurant(Long id) {
        this.id = id;
    }

    public Restaurant(Long id, String name, String location, CuisineType cuisineType,
                      List<OpeningHours> openingHours, int capacity, List<Reservation> reservations, List<Review> reviews) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome inválido");
        if (capacity <= 0) throw new IllegalArgumentException("Capacidade deve ser positiva");
        this.id = id;
        this.name = name;
        this.location = location;
        this.cuisine = cuisineType;
        this.openingHours = openingHours != null ? openingHours : new ArrayList<>();
        this.capacity = capacity;
        this.reservations = reservations != null ? reservations : new ArrayList<>();
        this.reviews = reviews != null ? reviews : new ArrayList<>();
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