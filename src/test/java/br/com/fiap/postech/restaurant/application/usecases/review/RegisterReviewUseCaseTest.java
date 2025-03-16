package br.com.fiap.postech.restaurant.application.usecases.review;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReviewDTO;
import br.com.fiap.postech.restaurant.application.gateway.ReservationRepository;
import br.com.fiap.postech.restaurant.application.gateway.RestaurantRepository;
import br.com.fiap.postech.restaurant.application.gateway.ReviewRepository;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.Restaurant;
import br.com.fiap.postech.restaurant.domain.entities.Review;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterReviewUseCaseTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private RegisterReviewUseCase registerReviewUseCase;

    private ReviewDTO reviewDTO;
    private User user;
    private Restaurant restaurant;
    private Review review;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        restaurant = new Restaurant();
        restaurant.setId(1L);

        reviewDTO = new ReviewDTO();
        reviewDTO.setId(null);
        reviewDTO.setUser(1L);
        reviewDTO.setRestaurant(1L);
        reviewDTO.setRating(5);
        reviewDTO.setComment("Ótima experiência!");
        reviewDTO.setUpdate(false);

        review = new Review();
        review.setId(1L);
        review.setUser(user);
        review.setRestaurant(restaurant);
        review.setRating(5);
        review.setComment("Ótima experiência!");
    }

    @Test
    void shouldRegisterReviewSuccessfully() {
        when(userRepository.findById(1L)).thenReturn(user);
        when(restaurantRepository.findById(1L)).thenReturn(restaurant);
        when(reviewRepository.hasUserReviewedRestaurant(1L, 1L)).thenReturn(false);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review savedReview = registerReviewUseCase.execute(reviewDTO);

        assertNotNull(savedReview);
        assertEquals(5, savedReview.getRating());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void shouldThrowExceptionWhenReviewDTOIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerReviewUseCase.execute(null));
        assertEquals("Dados da avaliação são obrigatórios", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserIdIsNull() {
        reviewDTO.setUser(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerReviewUseCase.execute(reviewDTO));
        assertEquals("ID do usuário é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantIdIsNull() {
        reviewDTO.setRestaurant(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerReviewUseCase.execute(reviewDTO));
        assertEquals("ID do restaurante é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRatingIsInvalid() {
        reviewDTO.setRating(6);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerReviewUseCase.execute(reviewDTO));
        assertEquals("A avaliação deve ser entre 1 e 5", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerReviewUseCase.execute(reviewDTO));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFound() {
        when(userRepository.findById(1L)).thenReturn(user);
        when(restaurantRepository.findById(1L)).thenReturn(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerReviewUseCase.execute(reviewDTO));
        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyReviewedAndNotUpdating() {
        when(userRepository.findById(1L)).thenReturn(user);
        when(restaurantRepository.findById(1L)).thenReturn(restaurant);
        when(reviewRepository.hasUserReviewedRestaurant(1L, 1L)).thenReturn(true);

        Exception exception = assertThrows(IllegalStateException.class, () -> registerReviewUseCase.execute(reviewDTO));
        assertEquals("O usuário já avaliou este restaurante", exception.getMessage());
    }
}