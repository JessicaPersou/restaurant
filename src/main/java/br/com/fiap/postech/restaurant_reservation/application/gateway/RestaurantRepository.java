package br.com.fiap.postech.restaurant_reservation.application.gateway;

import br.com.fiap.postech.restaurant_reservation.domain.entities.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
}
