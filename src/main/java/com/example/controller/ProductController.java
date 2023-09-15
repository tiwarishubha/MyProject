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
import com.example.binding.ProductRequest;
import com.example.binding.ProductResponse;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;

@RestController
public class ProductController 
{
	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@GetMapping("/api/products")
	public List<ProductResponse> getProducts () {
		List<Product> list = prodRepo.findAll();
		List<ProductResponse> responseList = new ArrayList<>();
		list.forEach(c -> {
			ProductResponse response = new ProductResponse();
			response.setProductId(c.getProductId());
			response.setProductName(c.getProductName());
			response.setCategoryName(c.getCategory().getCategoryName());
			response.setCategoryId(c.getCategory().getCategoryId());
			responseList.add(response);
		});
     	return responseList;
	}
	
	
	
	  @PostMapping("/api/products") 
	  public ResponseEntity<String> saveData(@RequestBody ProductRequest req) 
	  { 
	      Product product = new Product(req); 
	      Category category=new Category();
	      category.setCategoryName(req.getCategoryName()) ; 
	  
	      product.setCategory(category);
	  
	      catRepo.save(category);
	      prodRepo.save(product);

	      return new ResponseEntity<>("Data Saved Successfully", HttpStatus.CREATED); 
	  }
	
	@GetMapping("/api/products/{di}")
	public ProductResponse getProductById(@PathVariable(value="di") Integer di)
	{
		ProductResponse response = new ProductResponse();
		Optional<Product> coptional=prodRepo.findById(di);
		if(coptional.isPresent())
		{
			  Product product=coptional.get();
			  response.setProductId(product.getProductId());
			  response.setProductName(product.getProductName());
			  Category category=product.getCategory();
			  response.setCategoryId(category.getCategoryId());
			  response.setCategoryName(category.getCategoryName());	 
		}
		
		 return response;	
	}
	
	 @PutMapping("/api/products/{di}") 
	 public ProductResponse getProductById(@RequestBody ProductRequest req,@PathVariable(value="di") Integer di) 
	 { 
		 ProductResponse response = new ProductResponse(); 
		 Optional<Product> coptional=prodRepo.findById(di); 
		 if(coptional.isPresent())
	     {
			 Product product=coptional.get();
	         product.setProductName(req.getProductName()); 
	         prodRepo.save(product);
	         response.setProductId(product.getProductId());
	         response.setProductName(product.getProductName()); 
	         response.setCategoryName(product.getCategory().getCategoryName());
	         response.setCategoryId(product.getCategory().getCategoryId());
	     }	 
	 return response; 
	 }
	 
	
		/*
		 * @DeleteMapping("/product/{did}") public ResponseEntity<String>
		 * deleteById(@PathVariable(value="did") Integer did) {
		 * prodRepo.deleteById(did); return ResponseEntity.ok("Deleted Successfully"); }
		 */

}
