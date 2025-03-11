package br.com.fiap.postech.restaurant.application.usecases.review;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReviewDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.application.gateway.ReviewRepository;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.Review;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisterReviewUseCase {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReservationRepository reservationRepository;

    public RegisterReviewUseCase(ReviewRepository reviewRepository,
                                 UserRepository userRepository,
                                 RestaurantRepository restaurantRepository,
                                 ReservationRepository reservationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.reservationRepository = reservationRepository;
    }

    public Review execute(ReviewDTO reviewDTO) {
        if (reviewDTO == null) {
            throw new IllegalArgumentException("Dados da avaliação são obrigatórios");
        }

        if (reviewDTO.getUser() == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório");
        }

        if (reviewDTO.getRestaurant() == null) {
            throw new IllegalArgumentException("ID do restaurante é obrigatório");
        }

        if (reviewDTO.getRating() < 1 || reviewDTO.getRating() > 5) {
            throw new IllegalArgumentException("A avaliação deve ser entre 1 e 5");
        }

        User user = userRepository.findById(reviewDTO.getUser());
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        Restaurant restaurant = restaurantRepository.findById(reviewDTO.getRestaurant());
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurante não encontrado");
        }

        // Verifica se o usuário já avaliou o restaurante
        boolean hasReviewed = reviewRepository.hasUserReviewedRestaurant(
                reviewDTO.getUser(), reviewDTO.getRestaurant());

        if (hasReviewed && !reviewDTO.isUpdate()) {
            throw new IllegalStateException("O usuário já avaliou este restaurante");
        }

        Review review = new Review();
        review.setId(reviewDTO.getId()); // Será null para novas avaliações
        review.setUser(user);
        review.setRestaurant(restaurant);
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setReviewDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public List<Review> findByRestaurantId(Long restaurantId) {
        if (restaurantId == null) {
            throw new IllegalArgumentException("ID do restaurante é obrigatório");
        }

        return reviewRepository.findByRestaurantId(restaurantId);
    }

    public double calculateAverageRating(Long restaurantId) {
        if (restaurantId == null) {
            throw new IllegalArgumentException("ID do restaurante é obrigatório");
        }

        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        if (reviews.isEmpty()) {
            return 0.0;
        }

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
