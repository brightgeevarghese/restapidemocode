package edu.miu.cse.restfuldemo.repository;

import edu.miu.cse.restfuldemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    void deleteUserByUsername(String username);
}
