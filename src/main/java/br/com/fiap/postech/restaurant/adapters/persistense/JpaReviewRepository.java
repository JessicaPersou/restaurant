package br.com.fiap.postech.restaurant.adapters.persistense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends JpaRepository<ReviewData, Long> {
}
