package com.shashank.ecom.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shashank.ecom.DTO.CategoryDTO;
import com.shashank.ecom.DTO.ProductDTO;
import org.springframework.stereotype.Service;

import com.shashank.ecom.Exceptions.ProductNotFoundException;
import com.shashank.ecom.Repository.CategoryRepository;
import com.shashank.ecom.Repository.ProductRepository;
import com.shashank.ecom.models.Category;
import com.shashank.ecom.models.Product;
@Service
public class ProductService {
	
	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	
	public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public ProductDTO GetProduct(Long id) throws ProductNotFoundException{
		Optional<Product> GetASingleProductById;
		GetASingleProductById = productRepository.findById(id);
		
		Product SingleProduct = null;
		
		if(GetASingleProductById.isEmpty()) {
			throw new ProductNotFoundException("no product of that type in database");
		}
		else {
			SingleProduct = GetASingleProductById.get();
		}

		ProductDTO productDTO;
		productDTO = new ProductDTO();

		productDTO.setId(SingleProduct.getId());
		productDTO.setName(SingleProduct.getName());
		productDTO.setImage(SingleProduct.getImage());
		productDTO.setPrice(SingleProduct.getPrice());
		productDTO.setCategoryName(SingleProduct.getCategory().getName());

		return productDTO;
	}
	
	public List<ProductDTO> GetAllProducts(){
		List<Product> ProductFromDB ;
		ProductFromDB = productRepository.findAll();

		List<ProductDTO> productDTOS  = new ArrayList<>();

		for(Product p : ProductFromDB){
			ProductDTO productDTO;
			productDTO = new ProductDTO();

			productDTO.setId(p.getId());
			productDTO.setName(p.getName());
			productDTO.setImage(p.getImage());
			productDTO.setPrice(p.getPrice());
			productDTO.setCategoryName(p.getCategory().getName());

			productDTOS.add(productDTO);
		}

		return productDTOS;
	}

	public Product PostProduct(Product product) {
		Product saveProduct = new Product();
		
		Optional<Category> optionalcategory = categoryRepository.getCategoryByName(product.getCategory().getName());
		
		if(optionalcategory.isPresent()) {
			saveProduct.setCategory(optionalcategory.get());
		}
		else {
			Category c = new Category();
			c.setName(product.getCategory().getName());
			
			Category savedCategory = categoryRepository.save(c);
			
			saveProduct.setCategory(savedCategory);
			
		}
		saveProduct.setName(product.getName());
		saveProduct.setImage(product.getImage());
		saveProduct.setPrice(product.getPrice());
		
		return productRepository.save(saveProduct);
	}

//	public String deleteProduct(){
//		int count = productRepository.deleteProductWhereCategoryNull();
//		System.out.println(count);
//		return "Deleted All";
//	}

	// handle product not found exception
	public String deleteProduct(Long id){
		productRepository.deleteById(id);
		return "Deleted All";
	}

}
