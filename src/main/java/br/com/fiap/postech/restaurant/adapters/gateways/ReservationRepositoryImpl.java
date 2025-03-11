package br.com.fiap.postech.restaurant.adapters.gateways;

import br.com.fiap.postech.restaurant.adapters.persistense.JpaReservationRepository;
import br.com.fiap.postech.restaurant.adapters.persistense.ReservationData;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    private final JpaReservationRepository jpaReservationRepository;
    private final ModelMapper modelMapper;

    public ReservationRepositoryImpl(JpaReservationRepository jpaReservationRepository, ModelMapper modelMapper) {
        this.jpaReservationRepository = jpaReservationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationData reservationData;
        reservationData = modelMapper.map(reservation, ReservationData.class);
        reservationData = jpaReservationRepository.save(reservationData);
        return modelMapper.map(reservationData, Reservation.class);
    }

    @Override
    public List<Reservation> findByRestaurantAndDate(Long restaurantId, LocalDateTime date) {
        List<ReservationData> reservations = jpaReservationRepository.findByRestaurantIdAndReservationDate(restaurantId, date);
        return reservations.stream()
                .map(reservationData -> modelMapper.map(reservationData, Reservation.class))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation findById(Long id) {
        ReservationData reservationData = jpaReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
        return modelMapper.map(reservationData, Reservation.class);
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        List<ReservationData> reservations = jpaReservationRepository.findByUserId(userId);
        return reservations.stream()
                .map(reservationData -> modelMapper.map(reservationData, Reservation.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByRestaurantId(Long restaurantId) {
        List<ReservationData> reservations = jpaReservationRepository.findByRestaurantId(restaurantId);
        return reservations.stream()
                .map(reservationData -> modelMapper.map(reservationData, Reservation.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<Reservation> findByDateRange(LocalDateTime start, LocalDateTime end) {
        List<ReservationData> reservations = jpaReservationRepository.findByReservationDateBetween(start, end);
        return reservations.stream()
                .map(reservationData -> modelMapper.map(reservationData, Reservation.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByDateRangeAndRestaurant(LocalDateTime start, LocalDateTime end, Long restaurantId) {
        List<ReservationData> reservations = jpaReservationRepository.findByReservationDateBetweenAndRestaurantId(
                start, end, restaurantId);
        return reservations.stream()
                .map(reservationData -> modelMapper.map(reservationData, Reservation.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaReservationRepository.deleteById(id);
    }
}