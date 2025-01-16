package com.shashank.ecom.controllers;


import java.util.List;

import com.shashank.ecom.DTO.ProductDTO;
import org.springframework.web.bind.annotation.*;

import com.shashank.ecom.Services.ProductService;
import com.shashank.ecom.models.Product;


@RestController
public class ProductController {

	ProductService ProductService;
	
	public ProductController(ProductService productService) {
        this.ProductService = productService;
    }
	@GetMapping("/products/{id}")
	public ProductDTO GetProduct(@PathVariable("id") Long id)
	{
		
		ProductDTO prfdb = ProductService.GetProduct(id);
		
		return prfdb;
	}

	@GetMapping("/products")
	public List<ProductDTO> GetAllProduccts() {
		List<ProductDTO> allprfdb = ProductService.GetAllProducts();
		return allprfdb;
		
	}

	@PostMapping("/products")
	public Product PostProduct(@RequestBody Product productfromuser) {
		Product saveProduct = ProductService.PostProduct(productfromuser);
		
		return saveProduct;
	}


	@DeleteMapping("/products/{id}")
	public String DeleteProduct(@PathVariable("id") Long id){
		return ProductService.deleteProduct(id);
	}

}
