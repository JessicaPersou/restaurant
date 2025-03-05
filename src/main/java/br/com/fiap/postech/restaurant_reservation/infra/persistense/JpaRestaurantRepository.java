package br.com.fiap.postech.restaurant_reservation.infra.persistense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaRestaurantRepository extends JpaRepository<RestaurantData, Long> {

    @Query("SELECT r FROM RestaurantData r WHERE r.name = :name OR r.location = :location OR CAST(r.cuisine AS string) = :cuisine")
    List<RestaurantData> findRestaurantDataByNameOrLocationOrCuisine(String name, String location, String cuisine);
}
