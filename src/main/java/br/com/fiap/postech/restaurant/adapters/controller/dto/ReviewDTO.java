package br.com.fiap.postech.restaurant.adapters.controller.dto;


public class ReviewDTO {
    private Long id;
    private Long user;
    private Long restaurant;
    private int rating;
    private String comment;
    private boolean isUpdate;

    // Construtores, getters e setters
    public ReviewDTO() {
    }

    public ReviewDTO(Long id, Long user, Long restaurant, int rating, String comment) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Long restaurant) {
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

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }
}