package com.meteoradesigner.repository;

import com.meteoradesigner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This interface @code{DataJpaUserRepository} declares the data jpa @code{user} repository.
 */
public interface DataJpaUserRepository extends JpaRepository<User, Integer>, GenericCustomizedRepository<User,
        Integer> {

    //TODO doc and check to rename par
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

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