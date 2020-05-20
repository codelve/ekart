package com.infystore.ekart.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infystore.ekart.dto.LoginDTO;
import com.infystore.ekart.dto.SignUpDTO;
import com.infystore.ekart.exception.ApplicationException;
import com.infystore.ekart.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService custService;

	// Create a new customer
	@PostMapping(value = "/signup")
	public ResponseEntity<?> createCustomer(@RequestBody SignUpDTO custDTO) throws ApplicationException {
		logger.info("Creation request for customer {}", custDTO);
		return custService.createCustomer(custDTO);
	}

	// Login
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) throws ApplicationException {
		logger.info("Login request for customer {} with password {}", loginDTO.getUsername(), loginDTO.getPassword());
		return custService.login(loginDTO);
	}

}
