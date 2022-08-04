package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DetailImportGoods {
    @Id
    @Column(name = "idDetailImportGoods", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idIngredient")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idImportGoods")
    private ImportGoods importGoods;

    @Column(name = "quantity")
    private Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public ImportGoods getImportGoods() {
        return importGoods;
    }

    public void setImportGoods(ImportGoods importGoods) {
        this.importGoods = importGoods;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}