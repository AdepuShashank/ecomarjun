package com.shashank.ecom.controllers;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.springframework.web.bind.annotation.*;

import com.shashank.ecom.Services.CategoryService;
import com.shashank.ecom.models.Category;

@RestController
public class CategoryController {
	
	CategoryService CategoryService;
	
	public CategoryController(CategoryService CategoryService) {
		this.CategoryService = CategoryService;
	}
	@GetMapping("/category/{id}")
	public Category GetSingleCat(@PathVariable("id") int id) {
		Category cat = CategoryService.GetSingleCat(id);
		
		return cat;
	}

	@GetMapping("/categories")
	public List<Category> GetAllCat()
	{
		List<Category> allcat = CategoryService.GetAllCat();
		
		return allcat;
	}

	@PostMapping("/categories")
	public Category SaveCategory(@RequestBody Category catbyuser) {
		Category savecat = CategoryService.SaveCategory(catbyuser);
		return savecat;
	}

	@DeleteMapping("/categories/{id}")
	public String DeleteProduct(@PathVariable("id") Long id){
		return CategoryService.deleteCategory(id);
	}
}
