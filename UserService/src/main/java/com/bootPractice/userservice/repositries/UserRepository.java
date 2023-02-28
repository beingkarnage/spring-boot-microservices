package com.bootPractice.userservice.repositries;

import com.bootPractice.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
