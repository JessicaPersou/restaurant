package br.com.fiap.postech.restaurant.infra.controller.dto;

import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.User;
import br.com.fiap.postech.restaurant.domain.enums.Status;
import java.time.LocalDateTime;
import lombok.*;

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


