package br.com.fiap.postech.restaurant.infra.controller.dto;

import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Review;
import br.com.fiap.postech.restaurant.domain.enums.CuisineType;
import java.time.LocalTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private Long id;
    private String name;
    private String location;
    private CuisineType cuisine;
    private LocalTime openingHours;
    private int capacity;
    private List<Reservation> reservations;
    private List<Review> reviews;
}