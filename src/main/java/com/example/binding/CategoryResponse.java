package com.example.binding;

import java.util.List;

import com.example.entity.Product;

public class CategoryResponse {

    private Integer categoryId;
	
	private String categoryName;
	
	//private List<String> productName;
	
	//private List<Product> productList;
	
	/*
	 * public List<Product> getProductList() { return productList; }
	 * 
	 * public void setProductList(List<Product> productList) { this.productList =
	 * productList; }
	 */

	
	/*
	 * public List<String> getProductName() { return productName; }
	 * 
	 * void setProductName(List<String> productName) { this.productName =
	 * productName; }
	 */

	

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
}
