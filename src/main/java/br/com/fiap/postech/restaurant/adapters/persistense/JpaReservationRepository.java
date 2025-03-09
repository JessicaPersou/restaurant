package br.com.fiap.postech.restaurant.adapters.persistense;

import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaReservationRepository extends JpaRepository<ReservationData, Long> {
    @Query("SELECT r FROM ReservationData r WHERE r.restaurant.id = :restaurantId AND r.reservationDate = :date")
    List<Reservation> findByRestaurantAndDate(@Param("restaurantId") Long restaurantId, @Param("date") LocalDateTime date);
}
