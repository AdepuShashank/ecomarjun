package com.shashank.ecom.controllers;


import java.util.List;


import com.shashank.ecom.Mapper.ProductMapper;
import org.springframework.web.bind.annotation.*;


import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.Services.ProductService;
import com.shashank.ecom.models.Product;


@RestController
public class ProductController {

	ProductService ProductService;
	ProductMapper productMapper;
	
	public ProductController(ProductService productService, ProductMapper productMapper) {
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

	@PostMapping("/products")
	public Product PostProduct(@RequestBody Product productfromuser) {
		Product saveProduct = ProductService.PostProduct(productfromuser);
		
		return saveProduct;
	}

	// double - cant hold null vals, so it converts null to 0.0
	// Double class - can hold null vals

	@PutMapping("/products/{id}")
	public ProductDTO UpdateProduct(
			@PathVariable("id") Long id,
			@RequestBody ProductDTO product
			){

		System.out.println(product.toString());

		ProductDTO updatedProduct;
		updatedProduct = ProductService.UpdateProduct(
				id,
				product.getName(),
				product.getPrice(),
				product.getImage(),
				product.getCategoryName()
		);

		return updatedProduct;
	}

	
	@DeleteMapping("/products/{id}")
	public String DeleteProduct(@PathVariable("id") long id) {
		return ProductService.deleteProduct(id);
	}

}
