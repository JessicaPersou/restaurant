package br.com.fiap.postech.restaurant.application.usecases.restaurant;

import br.com.fiap.postech.restaurant.adapters.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RegisterRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;

    public RegisterRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(Restaurant restaurant) {
        if (restaurant.getName() == null) {
            throw new IllegalArgumentException("Dados inválidos");
        }

//        FilterRestaurantDTO filters = new FilterRestaurantDTO(restaurant.getName(), restaurant.getLocation(), restaurant.getCuisine().name());
//        boolean restaurantExist = restaurantRepository.findRestaurantByFilters(filters) ? true : false ;
//
//        if (restaurantExist) {
//            throw new IllegalArgumentException("Restaurante já cadastrado nesta localização!");
//        }
        return restaurantRepository.save(restaurant);
    }
}
