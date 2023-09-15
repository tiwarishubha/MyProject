package com.example.binding;

import java.util.List;

import com.example.entity.Product;

public class CategoryRequest {

	
	private String categoryName;
	private List<String> productName;
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getProductName() {
		return productName;
	}

	public void setProductName(List<String> productName) {
		this.productName = productName;
	}


}
