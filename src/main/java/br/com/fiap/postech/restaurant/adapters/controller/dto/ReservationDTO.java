package br.com.fiap.postech.restaurant.adapters.controller.dto;

import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.User;
import br.com.fiap.postech.restaurant.domain.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private User user;
    private Restaurant restaurant;
    private LocalDateTime reservationDate;
    private int numberOfPeople;
    private Status status;
}


