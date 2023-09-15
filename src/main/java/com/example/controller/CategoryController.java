package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.binding.CategoryRequest;
import com.example.binding.CategoryResponse;
import com.example.binding.ProductResponse;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private ProductRepository prodRepo;

	@GetMapping("/api/categories")
	public List<CategoryResponse> getData()
	{
		List<Category> list = catRepo.findAll();
		
		List<CategoryResponse> responseList = new ArrayList<>();

		list.forEach(l -> 
		{
			CategoryResponse response = new CategoryResponse();
			response.setCategoryId(l.getCategoryId());
			response.setCategoryName(l.getCategoryName());
	        responseList.add(response);

		});
		return responseList;
	}

	@PostMapping("/api/categories")
	public ResponseEntity<String> saveData(@RequestBody CategoryRequest req) 
	{	
		Category category = new Category(req);	
		category = catRepo.save(category);		
		List<Product> productList=new ArrayList<>();

		for (String s : req.getProductName()) {
			Product p = new Product();
			p.setProductName(s);
			p.setCategory(category);
			productList.add(p);
			
			prodRepo.save(p);
			category.setProductList(productList);
		}
		return new ResponseEntity<>("Data Saved Successfully", HttpStatus.CREATED);	
	}
	
	@GetMapping("/api/categories/{di}")
	public CategoryResponse getCategoryById(@PathVariable(value="di") Integer di)
	{
		CategoryResponse response = new CategoryResponse();
		Optional<Category> coptional=catRepo.findById(di);
		if(coptional.isPresent())
		{
			Category category=coptional.get();
			  response.setCategoryId(category.getCategoryId());
			  response.setCategoryName(category.getCategoryName());		 
		}
		
		 return response;
	}
	
	@PutMapping("/api/categories/{di}")
	public CategoryResponse getCategoryById(@RequestBody CategoryRequest req,@PathVariable(value="di") Integer di)
	{
		Optional<Category> coptional=catRepo.findById(di);
		CategoryResponse response = new CategoryResponse();
		if(coptional.isPresent())
		{
			Category category=coptional.get();
			category.setCategoryName(req.getCategoryName());
			catRepo.save(category);
			
			response.setCategoryId(category.getCategoryId());
			response.setCategoryName(category.getCategoryName());
		}
			
		return response;
	}
	
	@DeleteMapping("/api/categories/{di}")
	public ResponseEntity<String> deleteById(@PathVariable(value="di") Integer di)
	{
		catRepo.deleteById(di);
		return ResponseEntity.ok("Deleted Successfully");
	}
	 
}
