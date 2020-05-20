package com.infystore.ekart.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infystore.ekart.document.Role;
import com.infystore.ekart.document.User;
import com.infystore.ekart.dto.ERole;
import com.infystore.ekart.dto.JwtResponse;
import com.infystore.ekart.dto.LoginDTO;
import com.infystore.ekart.dto.SignUpDTO;
import com.infystore.ekart.dto.UserDTO;
import com.infystore.ekart.exception.AccountTypeException;
import com.infystore.ekart.exception.ApplicationException;
import com.infystore.ekart.exception.EmailIsAlreadyTakenException;
import com.infystore.ekart.exception.EntityNotFoundException;
import com.infystore.ekart.exception.FieldMissingException;
import com.infystore.ekart.exception.InvalidEmailException;
import com.infystore.ekart.exception.InvalidNameException;
import com.infystore.ekart.exception.InvalidPasswordException;
import com.infystore.ekart.exception.NoSuchUserException;
import com.infystore.ekart.exception.UserNameAlreadyTakenException;
import com.infystore.ekart.repository.RoleRepository;
import com.infystore.ekart.repository.UserRepository;
import com.infystore.ekart.security.jwt.JwtUtils;
import com.infystore.ekart.security.service.UserDetailsImpl;
import com.infystore.ekart.validator.SignUpValidator;

@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository custRepo;

	@Autowired
	SignUpValidator signUpValidator;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	public ResponseEntity<String> createCustomer(SignUpDTO userDTO)
			throws FieldMissingException, InvalidEmailException, InvalidNameException, InvalidPasswordException,
			EmailIsAlreadyTakenException, AccountTypeException, UserNameAlreadyTakenException {
		logger.info("Creation request for customer {}", userDTO);
		Objects.requireNonNull(userDTO, "user");
		signUpValidator.isValid(userDTO);

		if (userRepository.existsByUsername(userDTO.getUsername())) {
			throw new UserNameAlreadyTakenException("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(userDTO.getEmail())) {
			throw new EmailIsAlreadyTakenException("Email id is already used.");
		}

		// Create new user's account
		User user = new User(userDTO.getUsername(), userDTO.getEmail(), encoder.encode(userDTO.getPassword()));

		Set<String> strRoles = userDTO.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_GUEST)
					.orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "buyer":
					Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
							.orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));
					roles.add(buyerRole);

					break;

				case "seller":
					Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
							.orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));
					roles.add(sellerRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_GUEST)
							.orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok("User registered successfully!");
	}

	// Login
	public ResponseEntity<JwtResponse> login(LoginDTO loginDTO){

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	// Fetches full profile of a specific customer
	public UserDTO getCustomerProfile(String username) throws NoSuchUserException {

		logger.info("Profile request for customer {}", username);
		User user = custRepo.findByUsername(username).get();
		if (Objects.isNull(user))
			throw new NoSuchUserException("Error: User not found.");
		UserDTO custDTO = UserDTO.valueOf(user);
		logger.info("Profile for customer : {}", custDTO);
		return custDTO;
	}

	// fetch all user list
	public List<UserDTO> getAllCustomerProfile() {
		List<User> users = custRepo.findAll();
		return users.stream().map(u -> UserDTO.valueOf(u)).collect(Collectors.toList());
	}

	public ResponseEntity<?> updateCustomer(String userId, UserDTO userDTO) throws ApplicationException {
		signUpValidator.isValidEmail(userDTO.getEmail());
		signUpValidator.isValidName(userDTO.getName());
		User user = new User();
		try {
			BeanUtils.copyProperties(userDTO, user);
		} catch (IllegalAccessException e) {
			throw new ApplicationException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new ApplicationException(e.getMessage());
		}
		custRepo.save(user);
		return ResponseEntity.ok("User updated successfully!");

	}

}
