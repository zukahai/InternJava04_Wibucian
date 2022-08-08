package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class TypeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeTable", nullable = false, length = 15)
    private String id;

    @Column(name = "typeName", nullable = false, length = 100)
    private String typeName;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "typeTable")
    private Set<Tablecf> tablecfs = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Tablecf> getTablecfs() {
        return tablecfs;
    }

    public void setTablecfs(Set<Tablecf> tablecfs) {
        this.tablecfs = tablecfs;
    }

    @Override
    public String toString() {
        return "TypeTable{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", price=" + price +
                '}';
    }
}