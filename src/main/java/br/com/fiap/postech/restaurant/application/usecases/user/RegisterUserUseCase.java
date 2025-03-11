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
        if (user == null) {
            throw new IllegalArgumentException("Dados do usuário são obrigatórios");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email do usuário é obrigatório");
        }

        // Basic email validation
        if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de email inválido");
        }

        // Check if user with same email already exists
        // This assumes userRepository has a method to find by email
        // User existingUser = userRepository.findByEmail(user.getEmail());
        // if (existingUser != null) {
        //     throw new IllegalArgumentException("Usuário com este email já está cadastrado");
        // }

        return userRepository.save(user);
    }
}