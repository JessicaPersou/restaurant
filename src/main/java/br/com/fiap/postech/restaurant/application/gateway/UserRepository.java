package br.com.fiap.postech.restaurant.application.gateway;

import br.com.fiap.postech.restaurant.domain.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User save(User user);
    User findById(Long id);
}
