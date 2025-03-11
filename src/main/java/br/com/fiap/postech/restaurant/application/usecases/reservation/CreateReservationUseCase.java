package br.com.fiap.postech.restaurant.application.usecases.reservation;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.User;
import br.com.fiap.postech.restaurant.domain.enums.Status;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CreateReservationUseCase {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public CreateReservationUseCase(ReservationRepository reservationRepository,
                                    UserRepository userRepository,
                                    RestaurantRepository restaurantRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Reservation execute(ReservationDTO reservationDTO) {
        Reservation reservation = convertDtoToDomain(reservationDTO);

        // Check if restaurant is open at that time (missing check)
        if (!isRestaurantOpenAtTime(reservation.getRestaurant().getId(),
                reservation.getReservationDate())) {
            throw new IllegalStateException("Restaurante não está aberto neste horário.");
        }

        if (!isAvailable(reservation.getRestaurant().getId(),
                reservation.getReservationDate(),
                reservation.getNumberOfPeople())) {
            throw new IllegalStateException("Não há disponibilidade para esta reserva.");
        }

        return reservationRepository.save(reservation);
    }

    private boolean isRestaurantOpenAtTime(Long restaurantId, LocalDateTime dateTime) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurante não encontrado");
        }

        DayOfWeek day = dateTime.getDayOfWeek();
        LocalTime time = dateTime.toLocalTime();

        return restaurant.getOpeningHours().stream()
                .filter(hours -> hours.getDayOfWeek() == day)
                .anyMatch(hours -> hours.isTimeWithinBusinessHours(time));
    }

    private boolean isAvailable(Long restaurantId, LocalDateTime date, int numberOfPeople) {
        List<Reservation> existingReservations = reservationRepository.findByRestaurantAndDate(restaurantId, date);
        int occupiedSeats = existingReservations.stream().mapToInt(Reservation::getNumberOfPeople).sum();
        int capacity = restaurantRepository.getCapacity(restaurantId);
        return (occupiedSeats + numberOfPeople) <= capacity;
    }

    private Reservation convertDtoToDomain(ReservationDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dados da reserva são obrigatórios");
        }

        if (dto.getUser() == null) {
            throw new IllegalArgumentException("O ID do usuário é obrigatório");
        }
        User user = userRepository.findById(dto.getUser());
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        if (dto.getRestaurant() == null) {
            throw new IllegalArgumentException("O ID do restaurante é obrigatório");
        }
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurant());
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurante não encontrado");
        }

        if (dto.getReservationDate() == null) {
            throw new IllegalArgumentException("A data da reserva é obrigatória");
        }

        if (dto.getNumberOfPeople() <= 0) {
            throw new IllegalArgumentException("O número de pessoas deve ser maior que zero");
        }

        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setNumberOfPeople(dto.getNumberOfPeople());
        reservation.setStatus(dto.getStatus() != null ? dto.getStatus() : Status.PENDING); // Default status

        return reservation;
    }

    public ReservationDTO convertDomainToDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setReservationDate(reservation.getReservationDate());
        dto.setNumberOfPeople(reservation.getNumberOfPeople());
        dto.setStatus(reservation.getStatus());

        if (reservation.getUser() != null) {
            dto.setUser(reservation.getUser().getId());
        }

        if (reservation.getRestaurant() != null) {
            dto.setRestaurant(reservation.getRestaurant().getId());
        }

        return dto;
    }
}