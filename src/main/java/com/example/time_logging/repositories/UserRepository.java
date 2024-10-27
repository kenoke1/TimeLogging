package com.example.time_logging.repositories;

import com.example.time_logging.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
