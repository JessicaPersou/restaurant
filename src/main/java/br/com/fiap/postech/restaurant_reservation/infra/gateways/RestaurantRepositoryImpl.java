package br.com.fiap.postech.restaurant_reservation.infra.gateways;

import br.com.fiap.postech.restaurant_reservation.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant_reservation.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant_reservation.infra.persistense.JpaRestaurantRepository;
import br.com.fiap.postech.restaurant_reservation.infra.persistense.RestaurantData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final JpaRestaurantRepository jpaRestaurantRepository;
    private final ModelMapper modelMapper;

    public RestaurantRepositoryImpl(JpaRestaurantRepository jpaRestaurantRepository, ModelMapper modelMapper) {
        this.jpaRestaurantRepository = jpaRestaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantData restaurantData;
        restaurantData = modelMapper.map(restaurant, RestaurantData.class);
        restaurantData = jpaRestaurantRepository.save(restaurantData);
        return modelMapper.map(restaurantData, Restaurant.class);
    }
}

