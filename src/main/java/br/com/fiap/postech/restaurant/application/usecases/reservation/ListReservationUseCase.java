package br.com.fiap.postech.restaurant.application.usecases.reservation;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListReservationUseCase {
    private final ReservationRepository reservationRepository;
    private final CreateReservationUseCase createReservationUseCase;
    private final ModelMapper modelMapper;

    public ListReservationUseCase(ReservationRepository reservationRepository,
                                  CreateReservationUseCase createReservationUseCase, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.createReservationUseCase = createReservationUseCase;
        this.modelMapper = modelMapper;
    }

    public Reservation findById(Long id) {
        if (id == null) {
            throw new RuntimeException("Reserva não encontrada com o ID: " + id);
        }
        return reservationRepository.findById(id);
    }

    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public List<Reservation> findByRestaurantId(Long restaurantId) {
        return reservationRepository.findByRestaurantId(restaurantId);
    }

    public List<ReservationDTO> findAllByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório");
        }

        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream()
                .map(createReservationUseCase::convertDomainToDto)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> findAllByRestaurantId(Long restaurantId) {
        if (restaurantId == null) {
            throw new IllegalArgumentException("ID do restaurante é obrigatório");
        }

        List<Reservation> reservations = reservationRepository.findByRestaurantId(restaurantId);
        return reservations.stream()
                .map(createReservationUseCase::convertDomainToDto)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> findByDateRange(LocalDateTime start, LocalDateTime end, Long restaurantId) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Datas de início e fim são obrigatórias");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Data de início deve ser anterior à data de fim");
        }

        List<Reservation> reservations;
        if (restaurantId != null) {
            reservations = reservationRepository.findByDateRangeAndRestaurant(start, end, restaurantId);
        } else {
            reservations = reservationRepository.findByDateRange(start, end);
        }

        return reservations.stream()
                .map(createReservationUseCase::convertDomainToDto)
                .collect(Collectors.toList());
    }

    public List<Reservation> findByDateRangeAndRestaurant(LocalDateTime start, LocalDateTime end, Long restaurantId) {
        if (start == null || end == null || restaurantId == null) {
            throw new IllegalArgumentException("Start date, end date, and restaurant ID must be provided");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        return reservationRepository.findByDateRangeAndRestaurant(start, end, restaurantId);
    }
}