package com.meteoradesigner.repository;

import com.meteoradesigner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface @code{DataJpaUserRepository} declares the data jpa @code{user} repository.
 */
public interface DataJpaUserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds user by mail.
     *
     * @param mail given.
     * @return @code{User} by mail.
     */
    User findByEmail(String mail);

    /**
     * Finds user by display name.
     *
     * @param displayName given.
     * @return @code{User} by displayName.
     */
    User findByDisplayName(String displayName);
}