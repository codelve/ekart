package com.infystore.ekart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.infystore.ekart.dto.ProductDTO;
import com.infystore.ekart.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;


	@GetMapping("/products")
	public List<ProductDTO> getAllProducts() {
		logger.info("Fetching all products");
		return productService.getAllProducts();
	}


	@GetMapping("/products/mobiles")
	public List<ProductDTO> getAllMobiles() {
		logger.info("Fetching all mobiles");
		return productService.getAllMobiles();
	}


	@GetMapping("/products/tablets")
	public List<ProductDTO> getAllTablets() {
		logger.info("Fetching all tablets");
		return productService.getAllTablets();
	}

	@GetMapping(value = "/searchproduct/{productname}")
	public ProductDTO getSpecificProduct(@PathVariable String productname) {
		logger.info("Fetching details of plan {}", productname);
		return productService.getSpecificProduct(productname);
	}

}
