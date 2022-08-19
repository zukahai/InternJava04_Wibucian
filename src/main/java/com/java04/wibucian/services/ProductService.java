package com.java04.wibucian.services;

import com.java04.wibucian.dtos.ProductDTO;
import com.java04.wibucian.models.*;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.repositories.SaleRepository;
import com.java04.wibucian.repositories.TypeProductRepository;
import com.java04.wibucian.vos.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private SaleRepository saleRepository;
    private TypeProductRepository typeProductRepository;
    private DetailProductService detailProductService;

    public ProductService(ProductRepository productRepository, SaleRepository saleRepository, TypeProductRepository typeProductRepository, DetailProductService detailProductService) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.typeProductRepository = typeProductRepository;
        this.detailProductService = detailProductService;
    }


   public String save(ProductVO vO) {
        Product bean = new Product();
        TypeProduct typeProduct = this.typeProductRepository.findById(vO.getIdProductType())
                .orElseThrow(() -> new NoSuchElementException());
        BeanUtils.copyProperties(vO, bean);
        bean.setSale(saleRepository.findById(vO.getIdSale()).orElse(null));
        bean.setProductType(typeProduct);
        bean = productRepository.save(bean);
        return bean.getId();
    }
    public String save(ProductVO vO, String idSale) {
        Product product = new Product();
        BeanUtils.copyProperties(vO, product);
        Sale sale = saleRepository.findById(idSale).orElse(null);
        product.setSale(sale);

        TypeProduct typeProduct = this.typeProductRepository.findById(vO.getIdProductType())
                .orElseThrow(() -> new NoSuchElementException());
        product.setProductType(typeProduct);

        product = productRepository.save(product);
        return product.getId();
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }



    public Product get(String productId) {
        return productRepository.findById(productId)
                .get();
    }

    public String updateProductSaleNull(String idProduct) {
        Product product  = productRepository.findById(idProduct).orElse(null);
        product.setSale(null);
        product = productRepository.save(product);
        return product.getId();
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public List<Product> listSale(){
        return  productRepository.findAllBySaleNotNull();
    }

       public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(String id) {
        return requireOne(id);
    }


    public void update(String id, ProductUpdateVO vO) {
        Product bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        productRepository.save(bean);
    }

    public void update(String id, ProductUpdateVO vO, String idProductType) {
        Product bean = requireOne(id);
        String oldSrc = bean.getSrcImage();
        BeanUtils.copyProperties(vO, bean);
        if (vO.getSrcImage().equals("")){
            bean.setSrcImage(oldSrc);
        }

        TypeProduct typeProduct = typeProductRepository.findById(idProductType).orElse(null);
        bean.setProductType(typeProduct);

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

    public List<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }
    public List<Product> findAllNoPage( ) {
        return productRepository.findAll();
    }

    public void updateSale(String idProduct, String idSale){
        Product product= productRepository.findById(idProduct).orElse(null);
        Sale sale = saleRepository.findById(idSale).orElse(null);
        product.setSale(sale);
        productRepository.save(product);
    }

    public void updatePrice(String idProduct) {
        Product product = productRepository.findById(idProduct).orElse(null);
        product.setPrice(detailProductService.getProductSellPrice(idProduct));
        productRepository.save(product);
    }

    public List<Product> findAllHoang(Pageable pageable) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<Product> all = productRepository.findAll();
        List<Product> answer = new ArrayList();

        for (int i = start; i < start + pageable.getPageSize() && i < all.size(); i++) {
            answer.add(all.get(i));
        }
        return answer;
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil(productRepository.count() / (float)limit);
    }
}
