package br.com.fiap.postech.restaurant.adapters.controller.dto;

import br.com.fiap.postech.restaurant.domain.enums.Status;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long user;
    private Long restaurant;
    private LocalDateTime reservationDate;
    private int numberOfPeople;
    private Status status;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Long user, Long restaurant, LocalDateTime reservationDate, int numberOfPeople, Status status) {
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


