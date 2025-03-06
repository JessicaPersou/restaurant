package br.com.fiap.postech.restaurant.infra.gateways;

import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.infra.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.infra.persistense.JpaRestaurantRepository;
import br.com.fiap.postech.restaurant.infra.persistense.RestaurantData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<Restaurant> findRestaurantByFilters(FilterRestaurantDTO filter) {
        List<RestaurantData> restaurantList = jpaRestaurantRepository.findRestaurantDataByNameOrLocationOrCuisine
                (filter.getName(), filter.getLocation(), filter.getCuisine());
        if (restaurantList.isEmpty()) {
            return List.of();
        }
        return restaurantList.stream()
                .map(restaurantData ->
                modelMapper.map(restaurantData, Restaurant.class)).toList();
    }

}

