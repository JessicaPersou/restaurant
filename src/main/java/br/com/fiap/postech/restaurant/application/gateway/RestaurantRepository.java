package br.com.fiap.postech.restaurant.application.gateway;

import br.com.fiap.postech.restaurant.adapters.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    Restaurant findById(Long id);

    List<Restaurant> findAll();

    int getCapacity(Long restaurantId);

    List<Restaurant> findRestaurantByFilters(FilterRestaurantDTO filters);

    void delete(Long id);
}
