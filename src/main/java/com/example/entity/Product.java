package com.example.entity;



import com.example.binding.ProductRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	private String productName;
	
	@ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="category_id")
	private Category category;
	
	

	public Product(Integer productId, String productName, Category category) {
		
		this.productId = productId;
		this.productName = productName;
		this.category = category;
	}

	public Product() {
		
	}
	
	
	  public Product(ProductRequest req) 
	  { 
		  this.productName =req.getProductName();
	  }
	 
	 
	 

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category + "]";
	}
	
	
}

