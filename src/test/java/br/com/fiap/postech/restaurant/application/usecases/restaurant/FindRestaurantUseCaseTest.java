package br.com.fiap.postech.restaurant.application.usecases.restaurant;

import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FindRestaurantUseCase findRestaurantUseCase;

    @Test
    void shouldReturnRestaurantWhenFound() {
        Long restaurantId = 1L;
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName("Restaurante Teste");

        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);

        Restaurant result = findRestaurantUseCase.find(restaurantId);

        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals("Restaurante Teste", result.getName());
        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void shouldReturnNullWhenRestaurantNotFound() {
        Long restaurantId = 1L;
        when(restaurantRepository.findById(restaurantId)).thenReturn(null);

        Restaurant result = findRestaurantUseCase.find(restaurantId);

        assertNull(result);
        verify(restaurantRepository, times(1)).findById(restaurantId);
    }
}
