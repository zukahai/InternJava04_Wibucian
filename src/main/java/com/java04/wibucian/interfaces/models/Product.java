package com.java04.wibucian.interfaces.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSale")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProductType")
    private TypeProduct productType;

    @Lob
    @Column(name = "\"describe\"")
    private String describe;

    @Column(name = "srcImage", nullable = false, length = 100)
    private String srcImage;

    @Column(name = "productName", nullable = false, length = 100)
    private String productName;

    @OneToMany(mappedBy = "product")
    private Set<DetailInvoice> detailInvoices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Ordercf> ordercfs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<DetailProduct> detailProducts = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public TypeProduct getProductType() {
        return productType;
    }

    public void setProductType(TypeProduct productType) {
        this.productType = productType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Set<DetailInvoice> getDetailInvoices() {
        return detailInvoices;
    }

    public void setDetailInvoices(Set<DetailInvoice> detailInvoices) {
        this.detailInvoices = detailInvoices;
    }

    public Set<Ordercf> getOrdercfs() {
        return ordercfs;
    }

    public void setOrdercfs(Set<Ordercf> ordercfs) {
        this.ordercfs = ordercfs;
    }

    public Set<DetailProduct> getDetailProducts() {
        return detailProducts;
    }

    public void setDetailProducts(Set<DetailProduct> detailProducts) {
        this.detailProducts = detailProducts;
    }
}