package br.com.fiap.postech.restaurant.application.gateway;

import br.com.fiap.postech.restaurant.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> searchUsers(String searchTerm);

    void delete(Long id);
}
