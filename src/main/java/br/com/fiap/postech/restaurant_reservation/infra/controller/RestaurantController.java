package br.com.fiap.postech.restaurant_reservation.infra.controller;

import br.com.fiap.postech.restaurant_reservation.application.usecases.RegisterRestaurantUseCase;
import br.com.fiap.postech.restaurant_reservation.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant_reservation.infra.controller.dto.RestaurantDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RegisterRestaurantUseCase registerRestaurantUseCase;
    private final ModelMapper modelMapper;

    public RestaurantController(RegisterRestaurantUseCase registerRestaurantUseCase, ModelMapper modelMapper) {
        this.registerRestaurantUseCase = registerRestaurantUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantDTO dto) {
        Restaurant domainRestaurant = modelMapper.map(dto, Restaurant.class);
        Restaurant savedRestaurant = registerRestaurantUseCase.execute(domainRestaurant);
        RestaurantDTO response = modelMapper.map(savedRestaurant, RestaurantDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
