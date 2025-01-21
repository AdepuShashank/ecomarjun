package com.shashank.ecom.Services;

import java.util.List;

import java.util.Optional;

import com.shashank.ecom.Exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import com.shashank.ecom.Repository.CategoryRepository;
import com.shashank.ecom.models.Category;
@Service
public class CategoryService {
	CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category GetSingleCat(long id) throws CategoryNotFoundException {
		Optional<Category> getCatById = categoryRepository.findById(id);
		
		Category cat;
		
		if(getCatById.isEmpty())
		{
			throw new CategoryNotFoundException("Category not found in DB with ID : " + id );
		}
		else
		{
			cat = getCatById.get();
		}
		
		return cat;
	}

	public Category GetSingleCatByName(String name) throws CategoryNotFoundException {
		Optional<Category> getCatById = categoryRepository.getCategoryByName(name);

		Category cat;

		if(getCatById.isEmpty())
		{
			throw new CategoryNotFoundException("Category not found in DB with ID : " + name );
		}
		else
		{
			cat = getCatById.get();
		}

		return cat;
	}
	
	public List<Category> GetAllCat() {
		List<Category> allcat = categoryRepository.findAll();
		return allcat;
	}

	public Category SaveCategory(Category category) {
		Category savecat = categoryRepository.save(category);
		
		return savecat;
		
	}

	// create update category


	public String deletecategory(Long id) {
		categoryRepository.deleteById(id);
		
		return "Deleted";
	}
 
}
