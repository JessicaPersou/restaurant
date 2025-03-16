package br.com.fiap.postech.restaurant.adapters.persistense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaReservationRepository extends JpaRepository<ReservationData, Long> {
    @Query("SELECT r FROM ReservationData r WHERE r.restaurant.id = :restaurantId AND r.reservationDate = :date")
    List<ReservationData> findByRestaurantAndDate(@Param("restaurantId") Long restaurantId, @Param("date") LocalDateTime date);

    List<ReservationData> findByRestaurantIdAndReservationDate(Long restaurantId, LocalDateTime date);

    List<ReservationData> findByUserId(Long userId);

    List<ReservationData> findByRestaurantId(Long restaurantId);

    List<ReservationData> findByReservationDateBetween(LocalDateTime start, LocalDateTime end);

    List<ReservationData> findByReservationDateBetweenAndRestaurantId(
            LocalDateTime start, LocalDateTime end, Long restaurantId);

}
