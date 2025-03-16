package br.com.fiap.postech.restaurant.application.usecases.restaurant;

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
class RegisterRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RegisterRestaurantUseCase registerRestaurantUseCase;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Restaurante Teste");
        restaurant.setLocation("Rua A, 123");
        restaurant.setCuisine(CuisineType.ITALIAN);
        restaurant.setCapacity(50);
    }

    @Test
    void shouldRegisterRestaurantSuccessfully() {
        when(restaurantRepository.findRestaurantByFilters(any())).thenReturn(Collections.emptyList());
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant savedRestaurant = registerRestaurantUseCase.execute(restaurant);

        assertNotNull(savedRestaurant);
        assertEquals("Restaurante Teste", savedRestaurant.getName());
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerRestaurantUseCase.execute(null));
        assertEquals("Dados do restaurante são obrigatórios", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNameIsNull() {
        restaurant.setName(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerRestaurantUseCase.execute(restaurant));
        assertEquals("Nome do restaurante é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLocationIsNull() {
        restaurant.setLocation(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerRestaurantUseCase.execute(restaurant));
        assertEquals("Localização do restaurante é obrigatória", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCuisineIsNull() {
        restaurant.setCuisine(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerRestaurantUseCase.execute(restaurant));
        assertEquals("Tipo de culinária é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCapacityIsZeroOrNegative() {
        restaurant.setCapacity(0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerRestaurantUseCase.execute(restaurant));
        assertEquals("Capacidade deve ser positiva", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantAlreadyExists() {
        when(restaurantRepository.findRestaurantByFilters(any())).thenReturn(List.of(restaurant));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerRestaurantUseCase.execute(restaurant));
        assertEquals("Restaurante já cadastrado nesta localização!", exception.getMessage());
    }
}
