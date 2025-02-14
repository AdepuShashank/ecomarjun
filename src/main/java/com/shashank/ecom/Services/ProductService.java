package com.shashank.ecom.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.Exceptions.ProductNotFoundException;
import com.shashank.ecom.Mapper.ProductMapper;
import com.shashank.ecom.Repository.CategoryRepository;
import com.shashank.ecom.Repository.ProductRepository;
import com.shashank.ecom.models.Category;
import com.shashank.ecom.models.Product;
@Service
public class ProductService {
	
	CategoryService categoryService;
	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	ProductMapper productMapper;
	
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
		this.categoryService = categoryService;
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
		productDTO.setPrice(SingleProduct.getPrice());
		productDTO.setImage(SingleProduct.getImage());
		productDTO.setCategoryName(SingleProduct.getCategory().getName());
		
		return productDTO;
	}
	
	public List<ProductDTO> GetAllProducts(){
		List<Product> ProductFromDB ;
		ProductFromDB = productRepository.findAll();
		List<ProductDTO> productDTOS = new ArrayList<>();
		
		for(Product p : ProductFromDB) {
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setId(p.getId());
			productDTO.setName(p.getName());
			productDTO.setPrice(p.getPrice());
			productDTO.setImage(p.getImage());
			productDTO.setCategoryName(p.getCategory().getName());
			
			productDTOS.add(productDTO);
		}
		return productDTOS;
	}

	public ProductDTO PostProduct(String name, Double price, String image, String category) {

		Product saveProduct = new Product();
		
		saveProduct.setName(name);
		saveProduct.setImage(image);
		saveProduct.setPrice(price);
		Optional<Category> optionalcategory = categoryRepository.getCategoryByName(category);
		
		if(optionalcategory.isPresent()) {
			saveProduct.setCategory(optionalcategory.get());
		}
		else {
			Category c = new Category();
			c.setName(category);
			
			Category savedCategory = categoryRepository.save(c);
			
			saveProduct.setCategory(savedCategory);
			
		}
		
		return productMapper.toProductDTO(productRepository.save(saveProduct));
	}

	

	/// handle excp handln
	public String deleteProduct(long id) {
		productRepository.deleteById(id);
		return "Deleted product " + id;
	}

}
