package br.com.fiap.postech.restaurant.application.gateway;

import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository {
    Reservation save(Reservation reservation);

    //    Reservation findById(Long id);
    List<Reservation> findByRestaurantAndDate(Long restaurantId, LocalDateTime date);
//    void delete(Long id);
}
