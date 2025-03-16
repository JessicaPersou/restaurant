package br.com.fiap.postech.restaurant.domain.entities;

import br.com.fiap.postech.restaurant.domain.enums.Status;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private User user;
    private Restaurant restaurant;
    private LocalDateTime reservationDate;
    private int numberOfPeople;
    private Status status;

    public Reservation(long l, long l1, long l2, LocalDateTime now, LocalDateTime localDateTime) {
    }

    public Reservation(long id, long l, long l1, LocalDateTime now, LocalDateTime localDateTime, Status status) {
    }

    // Adicionado metodo para verificar se está dentro do horário de funcionamento
    public boolean isWithinBusinessHours() {
        return this.restaurant != null &&
                this.reservationDate != null &&
                this.restaurant.isOpenAt(this.reservationDate);
    }

    public Reservation() {
    }

    public Reservation(Long id) {
        this.id = id;
    }

    public Reservation(Long id, User user, Restaurant restaurant, LocalDateTime reservationDate, int numberOfPeople, Status status) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.reservationDate = reservationDate;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
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

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
