package br.com.fiap.postech.restaurant.adapters.gateways;

import br.com.fiap.postech.restaurant.adapters.persistense.JpaReservationRepository;
import br.com.fiap.postech.restaurant.adapters.persistense.ReservationData;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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
        jpaReservationRepository.save(reservationData);
        return modelMapper.map(reservationData, Reservation.class);
    }

    @Override
    public List<Reservation> findByRestaurantAndDate(Long restaurantId, LocalDateTime date) {
        return jpaReservationRepository.findByRestaurantAndDate(restaurantId, date);
    }
}
