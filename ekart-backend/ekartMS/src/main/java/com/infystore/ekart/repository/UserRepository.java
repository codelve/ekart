package com.infystore.ekart.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infystore.ekart.document.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);

	User findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
