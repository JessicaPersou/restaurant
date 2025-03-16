package br.com.fiap.postech.restaurant.adapters.gateways;

import br.com.fiap.postech.restaurant.adapters.persistense.JpaReviewRepository;
import br.com.fiap.postech.restaurant.adapters.persistense.JpaUserRepository;
import br.com.fiap.postech.restaurant.adapters.persistense.ReviewData;
import br.com.fiap.postech.restaurant.application.gateway.ReviewRepository;
import br.com.fiap.postech.restaurant.domain.entities.Review;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewRepositoryImpl implements ReviewRepository {

    private final JpaReviewRepository jpaReviewRepository;
    private final JpaUserRepository jpaUserRepository;
    private final ModelMapper modelMapper;

    public ReviewRepositoryImpl(JpaReviewRepository jpaReviewRepository,
                                JpaUserRepository jpaUserRepository,
                                ModelMapper modelMapper) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Review save(Review review) {
        ReviewData reviewData = modelMapper.map(review, ReviewData.class);
        reviewData = jpaReviewRepository.save(reviewData);
        return modelMapper.map(reviewData, Review.class);
    }

    @Override
    public List<Review> findByRestaurantId(Long restaurantId) {
        List<ReviewData> reviews = jpaReviewRepository.findByRestaurantId(restaurantId);
        return reviews.stream()
                .map(reviewData -> modelMapper.map(reviewData, Review.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasUserReviewedRestaurant(Long userId, Long restaurantId) {
        return jpaReviewRepository.existsByUserIdAndRestaurantId(userId, restaurantId);
    }

    @Override
    public Review findById(Long id) {
        ReviewData reviewData = jpaReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + id));
        return modelMapper.map(reviewData, Review.class);
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        List<ReviewData> reviews = jpaReviewRepository.findByUserId(userId);
        return reviews.stream()
                .map(reviewData -> modelMapper.map(reviewData, Review.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaReviewRepository.deleteById(id);
    }
}