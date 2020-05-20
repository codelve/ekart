package com.infystore.ekart.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infystore.ekart.document.Product;
import com.infystore.ekart.dto.ProductDTO;
import com.infystore.ekart.repository.ProductRepository;



@Service
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productRepo;
	
	// Fetches all plan details
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepo.findAll();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO planDTO = ProductDTO.valueOf(product);
			productDTOs.add(planDTO);
		}

		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	// Fetch specific plan details
	public ProductDTO getSpecificProduct(String productname) {
		logger.info("Plan details : {}", productname);
		return ProductDTO.valueOf(productRepo.findByDisplayName(productname));
	}

	public List<ProductDTO> getAllMobiles() {
		List<Product> products = productRepo.findByCategory("mobile");
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO planDTO = ProductDTO.valueOf(product);
			productDTOs.add(planDTO);
		}

		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	public List<ProductDTO> getAllTablets() {
		List<Product> products = productRepo.findByCategory("tablet");
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO planDTO = ProductDTO.valueOf(product);
			productDTOs.add(planDTO);
		}

		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}



}
