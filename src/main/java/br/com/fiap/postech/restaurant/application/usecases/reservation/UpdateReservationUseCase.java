package br.com.fiap.postech.restaurant.application.usecases.reservation;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.enums.Status;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class UpdateReservationUseCase {
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final CreateReservationUseCase createReservationUseCase;

    public UpdateReservationUseCase(ReservationRepository reservationRepository,
                                    RestaurantRepository restaurantRepository,
                                    CreateReservationUseCase createReservationUseCase) {
        this.reservationRepository = reservationRepository;
        this.restaurantRepository = restaurantRepository;
        this.createReservationUseCase = createReservationUseCase;
    }

    public ReservationDTO execute(ReservationDTO reservationDTO) {
        if (reservationDTO == null || reservationDTO.getId() == null) {
            throw new IllegalArgumentException("ID da reserva é obrigatório para atualização");
        }

        // Verifica se a reserva existe
        Reservation existingReservation = reservationRepository.findById(reservationDTO.getId());
        if (existingReservation == null) {
            throw new RuntimeException("Reserva não encontrada com o ID: " + reservationDTO.getId());
        }

        // Atualiza apenas os campos que foram modificados
        if (reservationDTO.getReservationDate() != null &&
                !reservationDTO.getReservationDate().equals(existingReservation.getReservationDate())) {

            // Verifica se o restaurante está aberto no novo horário
            if (!isRestaurantOpenAtTime(existingReservation.getRestaurant().getId(),
                    reservationDTO.getReservationDate())) {
                throw new IllegalStateException("Restaurante não está aberto neste horário");
            }

            existingReservation.setReservationDate(reservationDTO.getReservationDate());
        }

        if (reservationDTO.getNumberOfPeople() > 0 &&
                reservationDTO.getNumberOfPeople() != existingReservation.getNumberOfPeople()) {

            // Verificar disponibilidade para o novo número de pessoas
            if (!isAvailable(existingReservation.getRestaurant().getId(),
                    existingReservation.getReservationDate(),
                    reservationDTO.getNumberOfPeople(),
                    existingReservation.getNumberOfPeople())) {
                throw new IllegalStateException("Não há disponibilidade para esta alteração");
            }

            existingReservation.setNumberOfPeople(reservationDTO.getNumberOfPeople());
        }

        if (reservationDTO.getStatus() != null) {
            existingReservation.setStatus(reservationDTO.getStatus());
        }

        // Se houver mudança de restaurante (caso raro, mas possível)
        if (reservationDTO.getRestaurant() != null &&
                !reservationDTO.getRestaurant().equals(existingReservation.getRestaurant().getId())) {

            Restaurant newRestaurant = restaurantRepository.findById(reservationDTO.getRestaurant());
            if (newRestaurant == null) {
                throw new IllegalArgumentException("Restaurante não encontrado");
            }

            // Verificar disponibilidade no novo restaurante
            if (!isAvailable(newRestaurant.getId(),
                    existingReservation.getReservationDate(),
                    existingReservation.getNumberOfPeople(),
                    0)) {
                throw new IllegalStateException("Não há disponibilidade no novo restaurante");
            }

            existingReservation.setRestaurant(newRestaurant);
        }

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return createReservationUseCase.convertDomainToDto(updatedReservation);
    }

    public ReservationDTO updateStatus(Long reservationId, Status newStatus) {
        if (reservationId == null) {
            throw new IllegalArgumentException("ID da reserva é obrigatório");
        }

        if (newStatus == null) {
            throw new IllegalArgumentException("Novo status é obrigatório");
        }

        Reservation existingReservation = reservationRepository.findById(reservationId);
        if (existingReservation == null) {
            throw new RuntimeException("Reserva não encontrada com o ID: " + reservationId);
        }

        existingReservation.setStatus(newStatus);
        Reservation updatedReservation = reservationRepository.save(existingReservation);

        return createReservationUseCase.convertDomainToDto(updatedReservation);
    }

    public void cancelReservation(Long reservationId) {
        updateStatus(reservationId, Status.CANCELLED);
    }

    private boolean isRestaurantOpenAtTime(Long restaurantId, LocalDateTime dateTime) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurante não encontrado");
        }

        DayOfWeek day = dateTime.getDayOfWeek();
        LocalTime time = dateTime.toLocalTime();

        return restaurant.getOpeningHours().stream()
                .filter(hours -> hours.getDayOfWeek() == day && hours.isOpen())
                .anyMatch(hours -> hours.isTimeWithinBusinessHours(time));
    }

    private boolean isAvailable(Long restaurantId, LocalDateTime date, int newNumberOfPeople, int currentNumberOfPeople) {
        List<Reservation> existingReservations = reservationRepository.findByRestaurantAndDate(restaurantId, date);

        // Calcula o total de assentos ocupados, excluindo a reserva atual
        int occupiedSeats = existingReservations.stream()
                .mapToInt(Reservation::getNumberOfPeople)
                .sum() - currentNumberOfPeople;

        int capacity = restaurantRepository.getCapacity(restaurantId);
        return (occupiedSeats + newNumberOfPeople) <= capacity;
    }
}
