package com.shashank.ecom.Mapper;

import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.models.Category;
import com.shashank.ecom.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toProductDTO(Product p){
        ProductDTO productDTO;
        productDTO = new ProductDTO();

        productDTO.setId(p.getId());
        productDTO.setName(p.getName());
        productDTO.setPrice(p.getPrice());
        productDTO.setImage(p.getImage());
        productDTO.setCategoryName(p.getCategory().getName());

        System.out.println("test pd cat - " + p.getCategory().getName());

        return productDTO;
    }

    public Product toProduct(ProductDTO p){
        Product product;
        product = new Product();

        product.setId(p.getId());
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        product.setImage(p.getImage());

        product.setCategory(new Category(p.getCategoryName()));

        return product;

    }
}
