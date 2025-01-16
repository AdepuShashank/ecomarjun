package com.shashank.ecom.DTO;

import com.shashank.ecom.models.Category;

public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String image;
    private String categoryName;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, double price, String image, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
