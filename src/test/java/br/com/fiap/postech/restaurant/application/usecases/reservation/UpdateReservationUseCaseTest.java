package br.com.fiap.postech.restaurant.application.usecases.reservation;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CreateReservationUseCase createReservationUseCase;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UpdateReservationUseCase updateReservationUseCase;

    private Reservation reservation;
    private ReservationDTO reservationDTO;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        // Inicializa os objetos para os testes
        restaurant = new Restaurant(1L, "Restaurant 1", 50, null);
        reservation = new Reservation(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), Status.PENDING);
        reservationDTO = new ReservationDTO(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), Status.PENDING);

        // Mocka a conversão do domínio para DTO
        when(createReservationUseCase.convertDomainToDto(any(Reservation.class))).thenReturn(reservationDTO);
    }

//    @Test
//    public void testExecute_ReservaExistente() {
//        // Arrange
//        when(reservationRepository.findById(1L)).thenReturn(reservation);
//        when(restaurantRepository.findById(1L)).thenReturn(restaurant);
//        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
//
//        // Act
//        ReservationDTO result = updateReservationUseCase.execute(reservationDTO);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(reservationDTO.getId(), result.getId());
//        verify(reservationRepository).findById(1L);
//        verify(reservationRepository).save(any(Reservation.class));
//    }
//
//    @Test
//    public void testExecute_ReservaNaoEncontrada() {
//        // Arrange
//        when(reservationRepository.findById(1L)).thenReturn(null);
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> updateReservationUseCase.execute(reservationDTO));
//        assertEquals("Reserva não encontrada com o ID: " + reservationDTO.getId(), exception.getMessage());
//    }
//
//    @Test
//    public void testExecute_RestauranteNaoAberto() {
//        // Arrange
//        LocalDateTime newReservationDate = LocalDateTime.now().plusHours(2);
//        reservationDTO.setReservationDate(newReservationDate);
//
//        when(reservationRepository.findById(1L)).thenReturn(reservation);
//        when(restaurantRepository.findById(1L)).thenReturn(restaurant);
//        when(restaurantRepository.getCapacity(1L)).thenReturn(50);
//        when(reservationRepository.findByRestaurantAndDate(anyLong(), any(LocalDateTime.class)))
//                .thenReturn(List.of(reservation));
//
//        // Mocka o método de horário do restaurante para retornar falso
//        when(restaurantRepository.findById(anyLong())).thenReturn(restaurant);
//        when(restaurantRepository.getCapacity(anyLong())).thenReturn(50);
//
//        // Act & Assert
//        IllegalStateException exception = assertThrows(IllegalStateException.class,
//                () -> updateReservationUseCase.execute(reservationDTO));
//        assertEquals("Restaurante não está aberto neste horário", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateStatus() {
//        // Arrange
//        Long reservationId = 1L;
//        Status newStatus = Status.CONFIRMED;
//        when(reservationRepository.findById(reservationId)).thenReturn(reservation);
//        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
//
//        // Act
//        ReservationDTO result = updateReservationUseCase.updateStatus(reservationId, newStatus);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(newStatus, result.getStatus());
//        verify(reservationRepository).findById(reservationId);
//        verify(reservationRepository).save(any(Reservation.class));
//    }
//
//    @Test
//    public void testUpdateStatus_ReservaNaoEncontrada() {
//        // Arrange
//        Long reservationId = 1L;
//        Status newStatus = Status.CONFIRMED;
//        when(reservationRepository.findById(reservationId)).thenReturn(null);
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> updateReservationUseCase.updateStatus(reservationId, newStatus));
//        assertEquals("Reserva não encontrada com o ID: " + reservationId, exception.getMessage());
//    }

    @Test
    public void testCancelReservation() {
        // Arrange
        Long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(reservation);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        updateReservationUseCase.cancelReservation(reservationId);

        // Assert
        verify(reservationRepository).save(any(Reservation.class));
        assertEquals(Status.CANCELLED, reservation.getStatus());
    }
}
