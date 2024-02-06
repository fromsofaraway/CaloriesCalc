package com.brow.caloriescalc.repository;

import com.brow.caloriescalc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    @Query("select u from User u JOIN fetch u.role")
    List<User> findAll();
    @Query("select u FROM User u join fetch u.role where u.id = :id")
    Optional<User> findById(Long id);
}
