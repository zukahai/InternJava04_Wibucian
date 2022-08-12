package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailProductDTO;
import com.java04.wibucian.models.DetailProduct;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.repositories.DetailProductRepository;
import com.java04.wibucian.repositories.IngredientRepository;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.vos.DetailProductQueryVO;
import com.java04.wibucian.vos.DetailProductUpdateVO;
import com.java04.wibucian.vos.DetailProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DetailProductService {

    @Autowired
    private DetailProductRepository detailProductRepository;
    private IngredientRepository ingredientRepository;
    private ProductRepository productRepository;

    public DetailProductService(IngredientRepository ingredientRepository, ProductRepository productRepository1) {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository1;
    }

    public String save(DetailProductVO vO) {
        DetailProduct bean = new DetailProduct();
        BeanUtils.copyProperties(vO, bean);
        bean = detailProductRepository.save(bean);
        return bean.getId();
    }

    public String save(String idProduct, String idIngredient, Float quantity) {
        Product product = productRepository.findById(idProduct).orElse(null);
        Ingredient ingredient = ingredientRepository.findById(idIngredient).orElse(null);

        DetailProduct bean = new DetailProduct();
        bean.setProduct(product);
        bean.setIngredient(ingredient);
        bean.setQuantity(quantity);
        bean = detailProductRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        detailProductRepository.deleteById(id);
    }

    public List<Ingredient> getIngredientNotSelect(String idProduct){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        for(DetailProduct detailProduct : findAllByProductId(idProduct)) {
            ingredients.remove(detailProduct.getIngredient());
        }
        return ingredients;
    }

    public void update(String id, DetailProductUpdateVO vO) {
        DetailProduct bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        detailProductRepository.save(bean);
    }

    public DetailProductDTO getById(String id) {
        DetailProduct original = requireOne(id);
        return toDTO(original);
    }

    public DetailProduct findById(String id) {
        return requireOne(id);
    }

    public List<DetailProduct> findAllByProductId(String id) {
        return detailProductRepository.findAllByProductId(id);
    }

    public Float getPriceProductFormIngerdienrt(String idProduct){
        Float price = 0f;
        for(DetailProduct detailProduct : detailProductRepository.findAllByProductId(idProduct)){
            price += (detailProduct.getQuantity() * Float.valueOf(detailProduct.getIngredient().getPrice().toString()));
        }
        return price;
    }

    public Double getProductSellPrice(String idProduct) {
        int profit = 250; // profit(%)
        int x = 5000;
        Double priceSell = (profit / 100.0) * getPriceProductFormIngerdienrt(idProduct);
        priceSell = Math.ceil((priceSell + x) / 1000) * 1000 ;
        return priceSell;
    }


    public Page<DetailProductDTO> query(DetailProductQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DetailProductDTO toDTO(DetailProduct original) {
        DetailProductDTO bean = new DetailProductDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DetailProduct requireOne(String id) {
        return detailProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
