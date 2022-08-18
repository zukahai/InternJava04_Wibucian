package com.java04.wibucian.services;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.models.TypeProduct;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.repositories.SaleRepository;
import com.java04.wibucian.repositories.TypeProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HomeService {

    private ProductRepository productRepository;
    private SaleRepository saleRepository;
    private TypeProductRepository typeProductRepository;
    private DetailProductService detailProductService;
    private EmployeeRepository employeeRepository;

    public HomeService(ProductRepository productRepository, SaleRepository saleRepository, TypeProductRepository typeProductRepository, DetailProductService detailProductService, EmployeeRepository employeeRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.typeProductRepository = typeProductRepository;
        this.detailProductService = detailProductService;
        this.employeeRepository = employeeRepository;
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
        return all.subList(0, Math.min(n, all.size()));
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public List<TypeProduct> findAllTypeProduct() {
        return typeProductRepository.findAll();
    }

    public List<TypeProduct> findAllTypeProductHaveProductNotNull() {
        List<TypeProduct> answer = new ArrayList();
        for (TypeProduct typeProduct : typeProductRepository.findAll()) {
            if (typeProduct.getProducts().size() > 0) {
                answer.add(typeProduct);
            }
        }
        return answer;
    }
}
