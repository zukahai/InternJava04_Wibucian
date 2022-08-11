package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIngredient", nullable = false, length = 15)
    private String id;

    @Column(name = "ingredientName", nullable = false, length = 100)
    private String ingredientName;

    @Column(name = "expiryIngredient")
    private Date expiryIngredient;

    @Column(name = "price", nullable = false)
    private Float price;

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

    public Date getExpiryIngredient() {
        return expiryIngredient;
    }

    public void setExpiryIngredient(Date expiryIngredient) {
        this.expiryIngredient = expiryIngredient;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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