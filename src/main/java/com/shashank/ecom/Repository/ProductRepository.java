package com.shashank.ecom.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shashank.ecom.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
