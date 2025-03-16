package br.com.fiap.postech.restaurant.application.usecases.reservation;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.User;
import br.com.fiap.postech.restaurant.domain.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private CreateReservationUseCase createReservationUseCase;

    private ReservationDTO reservationDTO;
    private User user;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setCapacity(10);

        reservationDTO = new ReservationDTO();
        reservationDTO.setUser(user.getId());
        reservationDTO.setRestaurant(restaurant.getId());
        reservationDTO.setReservationDate(LocalDateTime.now().plusDays(1));
        reservationDTO.setNumberOfPeople(4);
        reservationDTO.setStatus(Status.PENDING);
    }

//    @Test
//    void shouldCreateReservationSuccessfully() {
//        when(userRepository.findById(user.getId())).thenReturn(user);
//        when(restaurantRepository.findById(restaurant.getId())).thenReturn(restaurant);
//        when(reservationRepository.findByRestaurantAndDate(anyLong(), any())).thenReturn(Collections.emptyList());
//        when(restaurantRepository.getCapacity(anyLong())).thenReturn(10);
//        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Reservation reservation = createReservationUseCase.execute(reservationDTO);
//
//        assertNotNull(reservation);
//        assertEquals(user.getId(), reservation.getUser().getId());
//        assertEquals(restaurant.getId(), reservation.getRestaurant().getId());
//        assertEquals(4, reservation.getNumberOfPeople());
//        verify(reservationRepository, times(1)).save(any(Reservation.class));
//    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> createReservationUseCase.execute(reservationDTO));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(user);
        when(restaurantRepository.findById(restaurant.getId())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> createReservationUseCase.execute(reservationDTO));
        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

//    @Test
//    void shouldThrowExceptionWhenRestaurantIsClosed() {
//        when(userRepository.findById(user.getId())).thenReturn(user);
//        when(restaurantRepository.findById(restaurant.getId())).thenReturn(restaurant);
//
//        doThrow(new IllegalStateException("Restaurante não está aberto neste horário."))
//                .when(restaurantRepository).findById(anyLong());
//
//        assertThrows(IllegalStateException.class, () -> createReservationUseCase.execute(reservationDTO));
//    }

//    @Test
//    void shouldThrowExceptionWhenReservationExceedsCapacity() {
//        when(userRepository.findById(user.getId())).thenReturn(user);
//        when(restaurantRepository.findById(restaurant.getId())).thenReturn(restaurant);
//        when(reservationRepository.findByRestaurantAndDate(anyLong(), any())).thenReturn(List.of(new Reservation()));
//        when(restaurantRepository.getCapacity(restaurant.getId())).thenReturn(4);
//
//        Exception exception = assertThrows(IllegalStateException.class, () -> createReservationUseCase.execute(reservationDTO));
//        assertEquals("Não há disponibilidade para esta reserva.", exception.getMessage());
//    }
}
