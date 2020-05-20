package com.infystore.ekart.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infystore.ekart.document.Role;
import com.infystore.ekart.dto.ERole;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	Optional<Role> findByName(ERole name);

}
