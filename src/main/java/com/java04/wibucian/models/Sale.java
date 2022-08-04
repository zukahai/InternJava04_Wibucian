package com.java04.wibucian.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Sale  {
    @Id
    @Column(name = "idSale", nullable = false, length = 15)
    private String id;

    @Column(name = "pcent", nullable = false, length = 50)
    private String pcent;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "sale")
    private Set<Product> products = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPcent() {
        return pcent;
    }

    public void setPcent(String pcent) {
        this.pcent = pcent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}