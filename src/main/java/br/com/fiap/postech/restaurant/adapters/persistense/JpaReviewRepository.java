package br.com.fiap.postech.restaurant.adapters.persistense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReviewRepository extends JpaRepository<ReviewData, Long> {
    List<ReviewData> findByRestaurantId(Long restaurantId);

    boolean existsByUserIdAndRestaurantId(Long userId, Long restaurantId);

    List<ReviewData> findByUserId(Long userId);
}
