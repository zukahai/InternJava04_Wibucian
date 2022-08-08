package com.java04.wibucian.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Ingredient {
    @Id
    @Column(name = "idIngredient", nullable = false, length = 15)
    private String id;

    @Column(name = "ingredientName", nullable = false, length = 100)
    private String ingredientName;

    @Column(name = "expiryIngredient")
    private Instant expiryIngredient;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "origin", nullable = false, length = 100)
    private String origin;

    @Column(name = "unit", nullable = false, length = 100)
    private String unit;

    @OneToMany(mappedBy = "ingredient")
    private Set<DetailImportGoods> detailImportGoods =
            new LinkedHashSet<>();

    @OneToMany(mappedBy = "ingredient")
    private Set<DetailProduct> detailProducts = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Instant getExpiryIngredient() {
        return expiryIngredient;
    }

    public void setExpiryIngredient(Instant expiryIngredient) {
        this.expiryIngredient = expiryIngredient;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<DetailImportGoods> getDetailImportGoods() {
        return detailImportGoods;
    }

    public void setDetailImportGoods(Set<DetailImportGoods> detailImportGoods) {
        this.detailImportGoods = detailImportGoods;
    }

    public Set<DetailProduct> getDetailProducts() {
        return detailProducts;
    }

    public void setDetailProducts(Set<DetailProduct> detailProducts) {
        this.detailProducts = detailProducts;
    }
}