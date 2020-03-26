package com.infosys.infystore.ekart.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infosys.infystore.ekart.document.Product;



public interface ProductRepository extends MongoRepository<Product, String> {

	Product findByProductId(int productId);

	List<Product> findByCategory(String string);

	Product findByDisplayName(String productName);


}
