package br.com.fiap.postech.restaurant.application.gateway;

import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.infra.controller.dto.FilterRestaurantDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    List<Restaurant> findRestaurantByFilters(FilterRestaurantDTO filter);
}
