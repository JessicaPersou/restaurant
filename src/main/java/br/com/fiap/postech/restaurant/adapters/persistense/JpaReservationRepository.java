package br.com.fiap.postech.restaurant.adapters.persistense;

import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaReservationRepository extends JpaRepository<ReservationData, Long> {
    List<Reservation> findByRestaurantAndDate(Long restaurantId, LocalDateTime date);

}
