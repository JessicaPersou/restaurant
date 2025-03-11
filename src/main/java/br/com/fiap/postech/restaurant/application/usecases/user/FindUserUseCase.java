package br.com.fiap.postech.restaurant.application.usecases.user;

import br.com.fiap.postech.restaurant.adapters.controller.dto.UserProfileDTO;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserUseCase {
    private final UserRepository userRepository;

    public FindUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório");
        }

        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }

        return user;
    }

    public User findByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email do usuário é obrigatório");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado com o email: " + email);
        }

        return user;
    }

    public List<User> searchUsers(String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            throw new IllegalArgumentException("Termo de busca é obrigatório");
        }

        return userRepository.searchUsers(searchTerm);
    }

    public UserProfileDTO getUserProfile(Long userId) {
        User user = findById(userId);

        // Supondo que você tenha um DTO para perfil de usuário
        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setName(user.getName());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setPhone(user.getPhone());

        // Contando estatísticas do usuário
        int reservationCount = user.getReservations().size();
        int reviewCount = user.getReviews().size();

        profileDTO.setReservationCount(reservationCount);
        profileDTO.setReviewCount(reviewCount);

        // Se quiser, pode calcular outras estatísticas, como restaurantes mais visitados

        return profileDTO;
    }
}