package br.com.fiap.postech.restaurant.infra.controller.dto;

import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Review;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<Reservation> reservations;
    private List<Review> reviews;
}
