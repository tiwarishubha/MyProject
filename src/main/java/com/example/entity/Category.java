package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.binding.CategoryRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	private String categoryName;
	
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Product> productList=new ArrayList<>();
	
	

	public Category() {
		
	}

	public Category(Integer categoryId, String categoryName, List<Product> productList) {
		
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.productList = productList;
	}

	public Category(CategoryRequest req) {
	 this.categoryName	=req.getCategoryName();
	 
	}

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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", productList=" + productList
				+ "]";
	};
	
	

}
