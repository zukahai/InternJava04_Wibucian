package com.java04.wibucian.models;

import javax.persistence.*;

@Entity
@Table(name = "DetailImportGoods")
public class DetailImportGood {
    @Id
    @Column(name = "idDetailImportGoods", nullable = false, length = 15)
    private String id;

    @Column(name = "id", nullable = false)
    private Integer id1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idIngredient")
    private Ingredient idIngredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idImportGoods")
    private ImportGood idImportGoods;

    @Column(name = "quantity")
    private Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public Ingredient getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Ingredient idIngredient) {
        this.idIngredient = idIngredient;
    }

    public ImportGood getIdImportGoods() {
        return idImportGoods;
    }

    public void setIdImportGoods(ImportGood idImportGoods) {
        this.idImportGoods = idImportGoods;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}