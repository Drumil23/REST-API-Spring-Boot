package com.javadrumil.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javadrumil.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
}
