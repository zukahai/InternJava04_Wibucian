package com.java04.wibucian.services;

import com.java04.wibucian.dtos.ProductDTO;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.models.TypeProduct;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.repositories.SaleRepository;
import com.java04.wibucian.repositories.TypeProductRepository;
import com.java04.wibucian.vos.ProductQueryVO;
import com.java04.wibucian.vos.ProductUpdateVO;
import com.java04.wibucian.vos.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HomeService {

    private ProductRepository productRepository;
    private SaleRepository saleRepository;
    private TypeProductRepository typeProductRepository;
    private DetailProductService detailProductService;

    public HomeService(ProductRepository productRepository, SaleRepository saleRepository, TypeProductRepository typeProductRepository, DetailProductService detailProductService) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.typeProductRepository = typeProductRepository;
        this.detailProductService = detailProductService;
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> findNumberRandomProduct(int n) {
        List<Product> all = productRepository.findAll();
        for (int i = 0; i < all.size() * all.size(); i++) {
            int x = (int)(Math.random() * 100000)% all.size();
            int y = (int)(Math.random() * 100000) % all.size();
            Collections.swap(all, x, y);
        }
        return all.subList(0, n);
    }

    public List<TypeProduct> findAllTypeProduct() {
        return typeProductRepository.findAll();
    }

}
