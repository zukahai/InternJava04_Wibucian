package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DetailImportGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailImportGoods", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idIngredient")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idImportGoods")
    private ImportGoods importGoods;

    @Column(name = "quantity")
    private Float quantity;

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

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DetailImportGoods{" +
                "id='" + id + '\'' +
                ", ingredient=" + ingredient +
                ", importGoods=" + importGoods +
                ", quantity=" + quantity +
                '}';
    }
}