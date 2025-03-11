package br.com.fiap.postech.restaurant.adapters.persistense;

import br.com.fiap.postech.restaurant.domain.enums.CuisineType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class RestaurantData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @Column(name = "cuisine_type")
    private CuisineType cuisine;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpeningHoursData> openingHours;
    private int capacity;
    @OneToMany(mappedBy = "restaurant")
    private List<ReservationData> reservations;
    @OneToMany(mappedBy = "restaurant")
    private List<ReviewData> reviews;

    public int getCapacity() {
        return capacity;
    }

}

