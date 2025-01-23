package com.shashank.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="\"Order\"")
// order is a reserved keyword in java,
// so need to escape it in jpa, if escaping not working then simply change table name to orders
public class Order extends BaseModel{

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    private Double totalAmount;
    private OrderStatus status;

    public Order(User user, List<Product> products, Double totalAmount, OrderStatus status) {
        this.user = user;
        this.products = products;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
