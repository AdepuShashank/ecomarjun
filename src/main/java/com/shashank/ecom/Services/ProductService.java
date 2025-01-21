package com.shashank.ecom.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shashank.ecom.DTO.CategoryDTO;
import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.Mapper.ProductMapper;
import org.springframework.stereotype.Service;

import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.Exceptions.ProductNotFoundException;
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

	// make it loosely coupled
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

	public ProductDTO UpdateProduct(Long id, String name, Double price, String image, String categoryName) throws ProductNotFoundException{
		Optional<Product> optionalProduct = productRepository.findById(id);

		if(optionalProduct.isEmpty()) {
			throw new ProductNotFoundException("no product of that type in database");
		}

		Product productToUpdate = optionalProduct.get();

		if(name != null){
			productToUpdate.setName(name);
		}
		if(price != null){
			productToUpdate.setPrice(price);
		}
		if(image != null){
			productToUpdate.setImage(image);
		}
		if(categoryName != null){
			// assumption, the categoryName which is coming, is already in category table of my DB
			Category categoryFromDB = categoryService.GetSingleCatByName(categoryName);
			productToUpdate.setCategory(categoryFromDB);
		}

		Product updatedProductFromDB;
		updatedProductFromDB = productRepository.save(productToUpdate);

		ProductDTO updatedProductDTO;
		updatedProductDTO = productMapper.toProductDTO(updatedProductFromDB);

		return updatedProductDTO;
	}

	public String deleteProduct(long id) {
		productRepository.deleteById(id);
		return "Deleted by id";
	}
}
