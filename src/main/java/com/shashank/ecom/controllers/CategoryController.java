package com.shashank.ecom.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
