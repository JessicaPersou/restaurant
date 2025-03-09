package br.com.fiap.postech.restaurant.application.usecases.user;

import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        if (user.getEmail() == null) {
            throw new RuntimeException("email vazio");
        }
        ;
        return userRepository.save(user);
    }
}
