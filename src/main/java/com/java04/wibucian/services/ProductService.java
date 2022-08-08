package com.java04.wibucian.services;

import com.java04.wibucian.dtos.ProductDTO;
import com.java04.wibucian.interfaces.models.Product;
import com.java04.wibucian.interfaces.models.TypeProduct;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.repositories.SaleRepository;
import com.java04.wibucian.repositories.TypeProductRepository;
import com.java04.wibucian.vos.ProductQueryVO;
import com.java04.wibucian.vos.ProductUpdateVO;
import com.java04.wibucian.vos.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

//    @Autowired
    private ProductRepository productRepository;
//
//    @Autowired
    private SaleRepository saleRepository;
//
//    @Autowired
    private TypeProductRepository typeProductRepository;

    public ProductService(ProductRepository productRepository, SaleRepository saleRepository, TypeProductRepository typeProductRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.typeProductRepository = typeProductRepository;
    }


    public String save(ProductVO vO) {
        Product bean = new Product();
        TypeProduct typeProduct = this.typeProductRepository.findById(vO.getIdProductType())
                .orElseThrow(() -> new NoSuchElementException());
        BeanUtils.copyProperties(vO, bean);
        bean.setSale(null);
        bean.setProductType(typeProduct);
        bean = productRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

    public Product get(String productId) {
        return productRepository.findById(productId)
                .get();
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }


    public void update(String id, ProductUpdateVO vO) {
        Product bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        productRepository.save(bean);
    }

    public ProductDTO getById(String id) {
        Product original = requireOne(id);
        return toDTO(original);
    }

    public Page<ProductDTO> query(ProductQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ProductDTO toDTO(Product original) {
        ProductDTO bean = new ProductDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Product requireOne(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
