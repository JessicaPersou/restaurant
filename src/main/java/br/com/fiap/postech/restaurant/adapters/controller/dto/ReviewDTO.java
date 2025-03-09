package br.com.fiap.postech.restaurant.adapters.controller.dto;

import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private User user;
    private Restaurant restaurant;
    private int rating;
    private String comment;
    private LocalDateTime reviewDate;
}
