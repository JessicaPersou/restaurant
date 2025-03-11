package br.com.fiap.postech.restaurant.domain.valueobjects;

import br.com.fiap.postech.restaurant.domain.entities.Restaurant;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OpeningHours {
    private DayOfWeek dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private boolean open;
    private Restaurant restaurant;


    // Adicionado construtor sem argumentos necessário para JPA
    public OpeningHours() {
    }

    public OpeningHours(DayOfWeek dayOfWeek, LocalTime openingTime, LocalTime closingTime, boolean open) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.open = open;
    }

    public boolean isTimeWithinBusinessHours(LocalTime time) {
        return this.isOpen() &&
                !time.isBefore(this.openingTime) &&
                !time.isAfter(this.closingTime);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
