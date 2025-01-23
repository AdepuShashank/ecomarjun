package com.shashank.ecom.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.Mapper.ProductMapper;
import com.shashank.ecom.Services.ProductService;
import com.shashank.ecom.models.Product;


@RestController
public class ProductController {

	ProductService ProductService;
	ProductMapper productMapper;
	
	public ProductController(ProductService productService,ProductMapper productMapper) {
        this.ProductService = productService;
        this.productMapper = productMapper;
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
	@PutMapping("products/{id}")
	public ProductDTO UpdateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO product)
	{
		
		ProductDTO updateProduct = ProductService.UpdateProduct(id,
				product.getName(),
				product.getPrice(),
				product.getImage(),
				product.getCategoryName());
		
		
		return updateProduct;
	}

	@PostMapping("/products")
	public ProductDTO PostProduct(@RequestBody ProductDTO productfromuser) {
		ProductDTO saveProduct = ProductService.PostProduct(
				productfromuser.getName(),
				productfromuser.getPrice(),
				productfromuser.getImage(),
				productfromuser.getCategoryName());
		
		return saveProduct;
	}

	
	@DeleteMapping("/products/{id}")
	public String DeleteProduct(@PathVariable("id") long id) {
		return ProductService.deleteProduct(id);
	}

}
