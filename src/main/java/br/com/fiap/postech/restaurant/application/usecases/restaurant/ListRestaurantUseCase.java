package br.com.fiap.postech.restaurant.application.usecases.restaurant;

import br.com.fiap.postech.restaurant.adapters.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;

    public ListRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> execute(FilterRestaurantDTO filter) {
        if (filter.getName() == null && filter.getLocation() == null && filter.getCuisine() == null) {
            throw new IllegalArgumentException("Elementos de busca estão vazios");
        }
        return restaurantRepository.findRestaurantByFilters(filter);
    }
}
