package com.infystore.ekart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infystore.ekart.dto.ProductDTO;
import com.infystore.ekart.service.WishlistService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/{userId}/")
public class WishlistController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WishlistService wishlistService;
	
	@PostMapping(value="/wishlist/{productName}/add")
	@PreAuthorize("hasRole('SELLER') or hasRole('BUYER') or hasRole('ADMIN')")
	public ResponseEntity<?> addWishlistProduct(@PathVariable("userId") String userId,
			@PathVariable("productName") String productName, @RequestBody ProductDTO wishlistItem) {
		logger.info("product add request to wishlist {}", productName);
		return wishlistService.addWishlistItem(userId, productName, wishlistItem);
	}
	
	@GetMapping(value="/wishlist")
	@PreAuthorize("hasRole('SELLER') or hasRole('BUYER') or hasRole('ADMIN')")
	public List<ProductDTO> getWishlist(@PathVariable("userId") String userId) {
		logger.info("Request for wishlist {}", userId);
		return wishlistService.getWishlist(userId);
	}
	
	@PostMapping(value="/wishlist/{productName}/remove")
	@PreAuthorize("hasRole('SELLER') or hasRole('BUYER') or hasRole('ADMIN')")
	public ResponseEntity<?> removeWishlistProduct(@PathVariable("userId") String userId,
			@PathVariable("productName") String productName) {
		logger.info("remove item request from wishlist {}", productName);
		return wishlistService.removeWishlistItem(userId, productName);
	}

}
