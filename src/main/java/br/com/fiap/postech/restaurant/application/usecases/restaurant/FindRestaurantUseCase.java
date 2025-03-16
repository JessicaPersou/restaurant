package br.com.fiap.postech.restaurant.application.usecases.restaurant;

import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class FindRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;

    public FindRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant find(Long id) {
        return restaurantRepository.findById(id);
    }
}
