package controllers;



//import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Services.ProductService;
import models.Product;


@RestController
public class ProductController {
	//ecom project

	ProductService ProductService;
	
	public ProductController(ProductService productService) {
        this.ProductService = productService;
    }
	@GetMapping("/product/{id}")
	public Product GetProduct(@PathVariable("id") int id)
	{
		System.out.println("hgsdvf");
		Product prfdb = ProductService.GetProduct(id);
		
		return prfdb;
		 
		
	}  
	
	
	
}
