package br.com.fiap.postech.restaurant_reservation.application.usecases;

import br.com.fiap.postech.restaurant_reservation.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant_reservation.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant_reservation.infra.controller.dto.FilterRestaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;

    public ListRestaurantUseCase(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> execute(FilterRestaurant filter){
        if(filter.getName() == null && filter.getLocation() == null && filter.getCuisine() == null){
            throw new IllegalArgumentException("Elementos de busca estão vazios");
        }
        return restaurantRepository.findRestaurantByFilters(filter);
    }
}
