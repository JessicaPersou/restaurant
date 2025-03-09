package br.com.fiap.postech.restaurant.domain.entities;

import java.time.LocalDateTime;

public class Review {
    private Long id;
    private User user;
    private Restaurant restaurant;
    private int rating;
    private String comment;
    private LocalDateTime reviewDate;

    public Review() {
    }

    public Review(Long id, User user, Restaurant restaurant,
                  int rating, String comment, LocalDateTime reviewDate) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
