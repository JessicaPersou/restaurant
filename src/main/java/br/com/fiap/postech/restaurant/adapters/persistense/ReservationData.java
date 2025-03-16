package br.com.fiap.postech.restaurant.adapters.persistense;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private int numberOfPeople;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantData restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerData customer; // Assumindo que existe uma classe CustomerData
}