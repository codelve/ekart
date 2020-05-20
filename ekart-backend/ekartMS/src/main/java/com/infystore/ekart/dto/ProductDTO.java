package com.infystore.ekart.dto;

import com.infystore.ekart.document.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@ToString
@Builder
public class ProductDTO {

	int productId;
	String displayName;
	String category;
	String shortDesc;
	String description;
	String imageUrl;
	int price;
	String manufacturer;
	String ostype;
	int rating;
	double discount;
	double deliveryCharge;
	
	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	// Converts Entity into DTO
	public static ProductDTO valueOf(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setDescription(product.getDescription());
		productDTO.setManufacturer(product.getManufacturer());
		productDTO.setOstype(product.getOstype());
		productDTO.setPrice(product.getPrice());
		productDTO.setShortDesc(product.getShortDesc());
		productDTO.setProductId(product.getProductId());
		productDTO.setDisplayName(product.getDisplayName());
		productDTO.setRating(product.getRating());
		productDTO.setCategory(product.getCategory());
		productDTO.setImageUrl(product.getImageUrl());
		productDTO.setDeliveryCharge(product.getDeliveryCharge());
		productDTO.setDiscount(product.getDiscount());
		return productDTO;
	}

	// Converts DTO into Entity
	public Product createEntity() {
		Product product = new Product();
		product.setDescription(this.getDescription());
		product.setManufacturer(this.getManufacturer());
		product.setOstype(this.getOstype());
		product.setPrice(this.getPrice());
		product.setShortDesc(this.getShortDesc());
		product.setProductId(this.getProductId());
		product.setDisplayName(this.getDisplayName());
		product.setRating(this.getRating());
		product.setCategory(this.getCategory());
		product.setImageUrl(this.getImageUrl());
		product.setDeliveryCharge(this.getDeliveryCharge());
		product.setDiscount(this.getDiscount());
		return product;
	}

}
