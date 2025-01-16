package com.shashank.ecom.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Product extends BaseModel{
		
		private String name;
		private double price;
		private String image;

		@ManyToOne
		@OnDelete(action = OnDeleteAction.CASCADE)
		private Category category;

		public Product(String name, Long id, double price, String image,Category category) {
			
			this.name = name;
			this.price = price;
			this.image = image;
			this.category = category;
		}

		public Product() {
			
		}
		
		public void setCategory(Category category) {
	        this.category = category;
	    }

	    public Category getCategory() {
	        return category;
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


		@Override
		public String toString() {
			return "Product [name=" + name + ", price=" + price + ", image=" + image + ", category=" + category + "]";
		}
		
	} 


	


