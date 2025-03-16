package br.com.fiap.postech.restaurant.adapters.persistense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByEmail(String email);

    List<UserData> findByNameContainingOrEmailContaining(String name, String email);

    boolean existsByEmail(String email);
}

