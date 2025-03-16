package br.com.fiap.postech.restaurant.application.usecases.reservation;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CreateReservationUseCase createReservationUseCase;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ListReservationUseCase listReservationUseCase;

    private Reservation reservation;
    private ReservationDTO reservationDTO;

    @BeforeEach
    public void setUp() {
        // Inicializa os objetos para os testes
        reservation = new Reservation(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        reservationDTO = new ReservationDTO(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        // Mocka a conversão do domínio para DTO
        when(createReservationUseCase.convertDomainToDto(any(Reservation.class))).thenReturn(reservationDTO);
    }

//    @Test
//    public void testFindById_ReservaExistente() {
//        // Arrange
//        Long id = 1L;
//        when(reservationRepository.findById(id)).thenReturn(reservation);
//
//        // Act
//        Reservation result = listReservationUseCase.findById(id);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(id, result.getId());
//        verify(reservationRepository).findById(id);
//    }
//
//    @Test
//    public void testFindById_ReservaNaoEncontrada() {
//        // Arrange
//        Long id = 1L;
//        when(reservationRepository.findById(id)).thenReturn(null);
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> listReservationUseCase.findById(id));
//        assertEquals("Reserva não encontrada com o ID: " + id, exception.getMessage());
//    }

    @Test
    public void testFindAllByUserId() {
        // Arrange
        Long userId = 1L;
        when(reservationRepository.findByUserId(userId)).thenReturn(List.of(reservation));

        // Act
        List<ReservationDTO> result = listReservationUseCase.findAllByUserId(userId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservationDTO, result.get(0));
        verify(reservationRepository).findByUserId(userId);
    }

    @Test
    public void testFindAllByRestaurantId() {
        // Arrange
        Long restaurantId = 1L;
        when(reservationRepository.findByRestaurantId(restaurantId)).thenReturn(List.of(reservation));

        // Act
        List<ReservationDTO> result = listReservationUseCase.findAllByRestaurantId(restaurantId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservationDTO, result.get(0));
        verify(reservationRepository).findByRestaurantId(restaurantId);
    }

    @Test
    public void testFindByDateRange() {
        // Arrange
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        Long restaurantId = 1L;
        when(reservationRepository.findByDateRangeAndRestaurant(start, end, restaurantId)).thenReturn(List.of(reservation));

        // Act
        List<ReservationDTO> result = listReservationUseCase.findByDateRange(start, end, restaurantId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservationDTO, result.get(0));
        verify(reservationRepository).findByDateRangeAndRestaurant(start, end, restaurantId);
    }

//    @Test
//    public void testFindByDateRange_InvalidDateRange() {
//        // Arrange
//        LocalDateTime start = LocalDateTime.now();
//        LocalDateTime end = LocalDateTime.now().minusDays(1);
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> listReservationUseCase.findByDateRange(start, end, null));
//        assertEquals("Data de início deve ser anterior à data de fim", exception.getMessage());
//    }
//
//    @Test
//    public void testFindByDateRange_MissingStartDate() {
//        // Arrange
//        LocalDateTime end = LocalDateTime.now().plusDays(1);
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> listReservationUseCase.findByDateRange(null, end, null));
//        assertEquals("Datas de início e fim são obrigatórias", exception.getMessage());
//    }
}
