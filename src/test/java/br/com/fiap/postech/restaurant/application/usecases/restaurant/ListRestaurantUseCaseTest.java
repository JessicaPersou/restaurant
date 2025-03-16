package br.com.fiap.postech.restaurant.application.usecases.restaurant;

import br.com.fiap.postech.restaurant.adapters.controller.dto.FilterRestaurantDTO;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.enums.CuisineType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private ListRestaurantUseCase listRestaurantUseCase;

    private FilterRestaurantDTO filter;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        filter = new FilterRestaurantDTO("Restaurante Teste", "Rua A, 123", "ITALIAN");
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Restaurante Teste");
        restaurant.setLocation("Rua A, 123");
        restaurant.setCuisine(CuisineType.ITALIAN);
        restaurant.setCapacity(50);
    }

    @Test
    void shouldReturnRestaurantsSuccessfully() {
        when(restaurantRepository.findRestaurantByFilters(filter)).thenReturn(List.of(restaurant));

        List<Restaurant> result = listRestaurantUseCase.execute(filter);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Restaurante Teste", result.get(0).getName());
        verify(restaurantRepository, times(1)).findRestaurantByFilters(filter);
    }

    @Test
    void shouldReturnEmptyListWhenNoRestaurantsFound() {
        when(restaurantRepository.findRestaurantByFilters(filter)).thenReturn(Collections.emptyList());

        List<Restaurant> result = listRestaurantUseCase.execute(filter);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(restaurantRepository, times(1)).findRestaurantByFilters(filter);
    }

    @Test
    void shouldThrowExceptionWhenFilterIsEmpty() {
        FilterRestaurantDTO emptyFilter = new FilterRestaurantDTO(null, null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> listRestaurantUseCase.execute(emptyFilter));
        assertEquals("Elementos de busca estão vazios", exception.getMessage());
    }
}
