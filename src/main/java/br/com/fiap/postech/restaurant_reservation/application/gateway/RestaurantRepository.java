package br.com.fiap.postech.restaurant_reservation.application.gateway;

import br.com.fiap.postech.restaurant_reservation.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant_reservation.infra.controller.dto.FilterRestaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    List<Restaurant> findRestaurantByFilters(FilterRestaurant filter);
}
