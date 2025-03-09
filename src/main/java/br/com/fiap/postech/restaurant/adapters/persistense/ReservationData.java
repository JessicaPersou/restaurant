package br.com.fiap.postech.restaurant.adapters.persistense;

import br.com.fiap.postech.restaurant.domain.enums.Status;
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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantData restaurant;
    @Column(name = "reservation_date")
    private LocalDateTime reservationDate;
    @Column(name = "number_of_people")
    private int numberOfPeople;
    private Status status;
}
