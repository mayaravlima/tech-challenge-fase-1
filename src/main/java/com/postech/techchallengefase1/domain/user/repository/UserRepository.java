package com.postech.techchallengefase1.domain.user.repository;

import com.postech.techchallengefase1.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameContainingIgnoreCase(String username);

    Optional<User> findByEmailContainingIgnoreCase(String email);

    Optional<User> findByUsernameAndEmailContainingIgnoreCase(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
