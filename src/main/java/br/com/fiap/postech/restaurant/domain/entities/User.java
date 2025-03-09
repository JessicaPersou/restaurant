package br.com.fiap.postech.restaurant.domain.entities;

import java.util.List;


public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<Reservation> reservations;
    private List<Review> reviews;

    public User() {
    }

    public User(Long id){
        this.id = id;
    }

    public User(Long id, String name, String email, String phone,
                List<Reservation> reservations, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
