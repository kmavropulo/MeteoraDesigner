package com.meteoradesigner.repository;

import com.meteoradesigner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaUserRepository extends JpaRepository<User, Integer> {
}
