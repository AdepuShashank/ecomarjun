package com.shashank.ecom.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.shashank.ecom.models.Product;
import com.shashank.ecom.models.Category;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM product WHERE category_id IS NULL", nativeQuery = true)
    int deleteProductWhereCategoryNull();
}
