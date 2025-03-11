package br.com.fiap.postech.restaurant.application.usecases.restaurant;

import br.com.fiap.postech.restaurant.adapters.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;

    public RegisterRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("Dados do restaurante são obrigatórios");
        }

        if (restaurant.getName() == null || restaurant.getName().isBlank()) {
            throw new IllegalArgumentException("Nome do restaurante é obrigatório");
        }

        if (restaurant.getLocation() == null || restaurant.getLocation().isBlank()) {
            throw new IllegalArgumentException("Localização do restaurante é obrigatória");
        }

        if (restaurant.getCuisine() == null) {
            throw new IllegalArgumentException("Tipo de culinária é obrigatório");
        }

        if (restaurant.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser positiva");
        }

        FilterRestaurantDTO filters = new FilterRestaurantDTO(
                restaurant.getName(),
                restaurant.getLocation(),
                restaurant.getCuisine().name()
        );

        List<Restaurant> existingRestaurants = restaurantRepository.findRestaurantByFilters(filters);
        if (existingRestaurants != null && !existingRestaurants.isEmpty()) {
            throw new IllegalArgumentException("Restaurante já cadastrado nesta localização!");
        }

        return restaurantRepository.save(restaurant);
    }
}