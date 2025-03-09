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
public class ReviewData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantData restaurant;
    private int rating;
    private String comment;
    @Column(name = "review_date")
    private LocalDateTime reviewDate;
}
