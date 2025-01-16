package com.shashank.ecom.controllers;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.ecom.Services.ProductService;
import com.shashank.ecom.models.Product;


@RestController
public class ProductController {
	//ecom project

	ProductService ProductService;
	
	public ProductController(ProductService productService) {
        this.ProductService = productService;
    }
	@GetMapping("/products/{id}")
	public Product GetProduct(@PathVariable("id") Long id)
	{
		
		Product prfdb = ProductService.GetProduct(id);
		
		return prfdb;
		 
		
	}  
	@GetMapping("/products")
	public List<Product> GetAllProduccts() {
		List<Product> allprfdb = ProductService.GetAllProducts();
		return allprfdb;
		
	}
	@PostMapping("/products")
	public Product PostProduct(@RequestBody Product productfromuser) {
		Product saveProduct = ProductService.PostProduct(productfromuser);
		
		return saveProduct;
	}
}
