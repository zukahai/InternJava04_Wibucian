package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Sale  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSale", nullable = false, length = 15)
    private String id;

    @Column(name = "pcent", nullable = false, length = 50)
    private String pcent;

    @OneToMany(mappedBy = "sale")
    private Set<Product> products = new LinkedHashSet<>();

    @Column(name = "timeStart")
    private Instant timeStart;

    @Column(name = "timeEnd")
    private Instant timeEnd;

    public Date getTimeStart() {
        return Date.from(this.timeStart);
    }

    public Date getTimeEnd() {
        return Date.from(this.timeEnd);
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }


    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}