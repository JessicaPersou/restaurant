package br.com.fiap.postech.restaurant_reservation.infra.persistense;

import br.com.fiap.postech.restaurant_reservation.domain.enums.CuisineType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @Column(name = "cusine_type")
    private CuisineType cuisine;
    @Column(name = "opening_hours")
    private LocalTime openingHours;
    private int capacity;
}
