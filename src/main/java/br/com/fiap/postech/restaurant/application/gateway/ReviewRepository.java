package br.com.fiap.postech.restaurant.application.gateway;

import br.com.fiap.postech.restaurant.domain.entities.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository {
    Review save(Review review);

    Review findById(Long id);

    List<Review> findByUserId(Long userId);

    List<Review> findByRestaurantId(Long restaurantId);

    boolean hasUserReviewedRestaurant(Long userId, Long restaurantId);

    void delete(Long id);
}
