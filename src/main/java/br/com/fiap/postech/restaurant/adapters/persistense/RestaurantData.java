package br.com.fiap.postech.restaurant.adapters.persistense;

import br.com.fiap.postech.restaurant.domain.enums.CuisineType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

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
    @Column(name = "cuisine_type")
    private CuisineType cuisine;
    @Column(name = "opening_hours")
    private LocalTime openingHours;
    private int capacity;
    @OneToMany(mappedBy = "restaurant")
    private List<ReservationData> reservations;
    @OneToMany(mappedBy = "restaurant")
    private List<ReviewData> reviews;
}