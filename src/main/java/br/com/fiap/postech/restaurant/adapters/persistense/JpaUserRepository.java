package br.com.fiap.postech.restaurant.adapters.persistense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserData, Long> {
}
