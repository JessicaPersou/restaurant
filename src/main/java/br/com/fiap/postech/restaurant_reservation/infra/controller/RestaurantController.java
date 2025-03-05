package br.com.fiap.postech.restaurant_reservation.infra.controller;

import br.com.fiap.postech.restaurant_reservation.application.usecases.ListRestaurantUseCase;
import br.com.fiap.postech.restaurant_reservation.application.usecases.RegisterRestaurantUseCase;
import br.com.fiap.postech.restaurant_reservation.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant_reservation.infra.controller.dto.FilterRestaurant;
import br.com.fiap.postech.restaurant_reservation.infra.controller.dto.RestaurantDTO;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RegisterRestaurantUseCase registerRestaurantUseCase;
    private final ListRestaurantUseCase listRestaurantUseCase;
    private final ModelMapper modelMapper;

    public RestaurantController(RegisterRestaurantUseCase registerRestaurantUseCase,
                                ListRestaurantUseCase listRestaurantUseCase,
                                ModelMapper modelMapper) {
        this.registerRestaurantUseCase = registerRestaurantUseCase;
        this.listRestaurantUseCase = listRestaurantUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantDTO dto) {
        Restaurant domainRestaurant = modelMapper.map(dto, Restaurant.class);
        Restaurant savedRestaurant = registerRestaurantUseCase.execute(domainRestaurant);
        RestaurantDTO response = modelMapper.map(savedRestaurant, RestaurantDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String cuisine
    ){
        FilterRestaurant filterRestaurant = new FilterRestaurant(name, location, cuisine);
        List<Restaurant> restaurants = listRestaurantUseCase.execute(filterRestaurant);
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);

    }
}
