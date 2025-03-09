package br.com.fiap.postech.restaurant.application.usecases.restaurant;

import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RegisterRestaurantUseCase registerRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterRestaurantSuccessfully() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");

        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant savedRestaurant = registerRestaurantUseCase.execute(restaurant);

        assertNotNull(savedRestaurant);
        assertEquals("Test Restaurant", savedRestaurant.getName());
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Restaurant restaurant = new Restaurant();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registerRestaurantUseCase.execute(restaurant);
        });

        assertEquals("Dados inválidos", exception.getMessage());
        verify(restaurantRepository, never()).save(any());
    }
}
