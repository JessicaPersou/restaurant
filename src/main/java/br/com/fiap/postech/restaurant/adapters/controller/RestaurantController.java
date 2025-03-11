package br.com.fiap.postech.restaurant.adapters.controller;

import br.com.fiap.postech.restaurant.adapters.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.adapters.controller.dto.RestaurantDTO;
import br.com.fiap.postech.restaurant.application.usecases.restaurant.FindRestaurantUseCase;
import br.com.fiap.postech.restaurant.application.usecases.restaurant.ListRestaurantUseCase;
import br.com.fiap.postech.restaurant.application.usecases.restaurant.RegisterRestaurantUseCase;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import jakarta.validation.Valid;
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
    private final FindRestaurantUseCase findRestaurantUseCase;
    private final ModelMapper modelMapper;

    public RestaurantController(RegisterRestaurantUseCase registerRestaurantUseCase,
                                ListRestaurantUseCase listRestaurantUseCase,
                                ModelMapper modelMapper,
                                FindRestaurantUseCase findRestaurantUseCase) {
        this.registerRestaurantUseCase = registerRestaurantUseCase;
        this.listRestaurantUseCase = listRestaurantUseCase;
        this.modelMapper = modelMapper;
        this.findRestaurantUseCase = findRestaurantUseCase;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@Valid @RequestBody RestaurantDTO dto) {
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
    ) {
        FilterRestaurantDTO filterRestaurantDTO = new FilterRestaurantDTO(name, location, cuisine);
        List<Restaurant> restaurants = listRestaurantUseCase.execute(filterRestaurantDTO);
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> restaurantById(@PathVariable Long id) {
        Restaurant find = findRestaurantUseCase.find(id);
        RestaurantDTO dto = modelMapper.map(find, RestaurantDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
