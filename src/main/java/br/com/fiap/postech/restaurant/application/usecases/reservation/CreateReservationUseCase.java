package br.com.fiap.postech.restaurant.application.usecases.reservation;

import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.User;
import br.com.fiap.postech.restaurant.domain.enums.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Reservation execute(Long userId, Long restaurantId, LocalDateTime reservationDate, int numberOfPeople) {
        User user = userRepository.findById(userId);

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (!isAvailable(restaurantId, reservationDate, numberOfPeople)) {
            throw new IllegalStateException("Não há disponibilidade para esta reserva.");
        }

        Reservation reservation = new Reservation(null, user, restaurant, reservationDate, numberOfPeople, Status.PENDING);
        return reservationRepository.save(reservation);
    }

    private boolean isAvailable(Long restaurantId, LocalDateTime date, int numberOfPeople) {
        List<Reservation> existingReservations = reservationRepository.findByRestaurantAndDate(restaurantId, date);
        int occupiedSeats = existingReservations.stream().mapToInt(Reservation::getNumberOfPeople).sum();
        int capacity = restaurantRepository.getCapacity(restaurantId);
        return (occupiedSeats + numberOfPeople) <= capacity;
    }
}