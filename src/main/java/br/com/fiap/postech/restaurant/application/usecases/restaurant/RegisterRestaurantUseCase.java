package br.com.fiap.postech.restaurant.application.usecases.restaurant;

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
//        implementacao futura
//        if (restaurantRepository.existsByNameAndLocation(restaurant.getName(), restaurant.getLocation())) {
//            throw new RestaurantAlreadyExistsException("Restaurante já cadastrado nesta localização!");
//        }
        return restaurantRepository.save(restaurant);
    }
}
