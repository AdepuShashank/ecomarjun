package com.shashank.ecom.Services;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shashank.ecom.Repository.CategoryRepository;
import com.shashank.ecom.models.Category;
@Service
public class CategoryService {
	CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category GetSingleCat(long id){
		Optional<Category> getCatById = categoryRepository.findById(id);
		
		Category cat = null;
		
		if(getCatById.isEmpty())
		{
			System.out.println("empty");
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
}
