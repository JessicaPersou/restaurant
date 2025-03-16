package br.com.fiap.postech.restaurant.adapters.persistense;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewText;
    private int rating;
    private LocalDate reviewDate;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantData restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerData customer; // Assumindo que existe uma classe CustomerData
}