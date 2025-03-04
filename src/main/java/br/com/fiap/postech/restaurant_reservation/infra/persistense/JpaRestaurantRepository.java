package br.com.fiap.postech.restaurant_reservation.infra.persistense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRestaurantRepository extends JpaRepository<RestaurantData, UUID> {
}
