package com.infystore.ekart.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infystore.ekart.document.Product;
import com.infystore.ekart.document.User;
import com.infystore.ekart.dto.ProductDTO;
import com.infystore.ekart.exception.EntityNotFoundException;
import com.infystore.ekart.repository.ProductRepository;
import com.infystore.ekart.repository.UserRepository;

@Service
public class WishlistService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<?> removeWishlistItem(String userId, String productName) {
		isValidUserIdAndProductName(userId, productName);
		User user = userRepository.findById(userId).get();
		Product product = productRepository.findByDisplayName(productName);
		user.getWishlist().remove(product);
		userRepository.save(user);
		return ResponseEntity.ok("Product addded to wishlist!");
	}

	public List<ProductDTO> getWishlist(String userId) {
		isValidUserId(userId);
		User user = userRepository.findById(userId).get();
		return user.getWishlist().stream().map(p -> ProductDTO.valueOf(p)).collect(Collectors.toList());
	}

	public ResponseEntity<?> addWishlistItem(String userId, String productName, ProductDTO wishlistItem) {
		isValidUserIdAndProductName(userId, productName);
		User user = userRepository.findById(userId).get();
		Product product = productRepository.findByDisplayName(productName);
		user.getWishlist().add(product);
		userRepository.save(user);
		return ResponseEntity.ok("Product addded to wishlist!");
	}
	
	private boolean isValidUserIdAndProductName(String userId, String productName) {

		return isValidUserId(userId) && isValidProductName(productName);
	}
	
	private boolean isValidUserId(String userId) {
		if (StringUtils.isBlank(userId))
			throw new EntityNotFoundException("Invalid user id");
		User user = userRepository.findById(userId).get();
		if (user == null)
			throw new EntityNotFoundException("Invalid user id");
		
		return true;
	}
	
	private boolean isValidProductName(String productName) {
		if (StringUtils.isBlank(productName) || Objects.isNull(productRepository.findByDisplayName(productName))) {
			throw new EntityNotFoundException("Invalid product name");
		}
		return true;
	}

}
