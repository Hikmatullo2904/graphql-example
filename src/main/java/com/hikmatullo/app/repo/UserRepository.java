package com.hikmatullo.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hikmatullo.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
