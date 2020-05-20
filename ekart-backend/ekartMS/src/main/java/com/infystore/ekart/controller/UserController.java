package com.infystore.ekart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infystore.ekart.dto.UserDTO;
import com.infystore.ekart.exception.ApplicationException;
import com.infystore.ekart.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService custService;

	@GetMapping(value = "/allUsers")
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserDTO> getCustomersProfile() throws ApplicationException {
		logger.info("Fetch all user details");
		List<UserDTO> custDTOs = custService.getAllCustomerProfile();
		return custDTOs;
	}

	@GetMapping(value = "/profile")
	@PreAuthorize("hasRole('GUEST') or hasRole('SELLER') or hasRole('BUYER') or hasRole('ADMIN')")
	public UserDTO getCustomerProfile() throws ApplicationException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("Profile request for customer {}", userDetails.getUsername());
		UserDTO custDTO = custService.getCustomerProfile(userDetails.getUsername());
		return custDTO;
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody UserDTO user)
			throws ApplicationException {
		return custService.updateCustomer(userId, user);
	}

}
