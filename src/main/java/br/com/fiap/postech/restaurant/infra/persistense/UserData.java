package br.com.fiap.postech.restaurant.infra.persistense;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    @OneToMany(mappedBy = "user")
    private List<ReservationData> reservations;
    @OneToMany(mappedBy = "user")
    private List<ReviewData> reviews;
}
