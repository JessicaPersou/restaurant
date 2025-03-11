package br.com.fiap.postech.restaurant.adapters.gateways;

import br.com.fiap.postech.restaurant.adapters.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.adapters.persistense.JpaRestaurantRepository;
import br.com.fiap.postech.restaurant.adapters.persistense.RestaurantData;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public Restaurant findById(Long id) {
        RestaurantData restaurantData = jpaRestaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + id));
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

    @Override
    public int getCapacity(Long restaurantId) {
        RestaurantData restaurantData = jpaRestaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        return restaurantData.getCapacity();
    }

    @Override
    public List<Restaurant> findAll() {
        List<RestaurantData> restaurants = jpaRestaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurantData -> modelMapper.map(restaurantData, Restaurant.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaRestaurantRepository.deleteById(id);
    }

}

