package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Tablecf  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTablecf", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypeTable")
    private TypeTable typeTable;

    @Lob
    @Column(name ="\"describe\"", nullable = false)
    private String describe;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "tablecf")
    private Set<DetailGroupTable> detailGroupTables = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeTable getTypeTable() {
        return typeTable;
    }

    public void setTypeTable(TypeTable typeTable) {
        this.typeTable = typeTable;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<DetailGroupTable> getDetailGroupTables() {
        return detailGroupTables;
    }

    public void setDetailGroupTables(Set<DetailGroupTable> detailGroupTables) {
        this.detailGroupTables = detailGroupTables;
    }

    @Override
    public String toString() {
        return "Tablecf{" +
                "id='" + id + '\'' +
                ", typeTable=" + typeTable +
                ", describe='" + describe + '\'' +
                ", capacity=" + capacity +
                ", detailGroupTables=" + detailGroupTables +
                '}';
    }
}