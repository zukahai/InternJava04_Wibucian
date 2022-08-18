package com.java04.wibucian.services;

import com.java04.wibucian.dtos.OrdercfDTO;
import com.java04.wibucian.models.*;
import com.java04.wibucian.repositories.*;
import com.java04.wibucian.vos.OrdercfQueryVO;
import com.java04.wibucian.vos.OrdercfUpdateVO;
import com.java04.wibucian.vos.OrdercfVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.util.*;

@Service
public class OrdercfService {

    @Autowired
    private OrdercfRepository ordercfRepository;

    @Autowired
    private OrdercfNoMapPingRepository ordercfNoMapPingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductNoMapPingRepository productNoMapPingRepository;

    @Autowired
    private GroupTableRepository groupTableRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    private GroupTableNoMapPingRepository groupTableNoMapPingRepository;
    @Autowired
    private DetailIngredientRepository detailIngredientRepository;

    public HashMap<String, Object> save(OrdercfVO vO) {
        HashMap<String, Object> map = new HashMap<>();
        Ordercf bean = new Ordercf();
        Set<DetailProduct> detailProducts;
        BeanUtils.copyProperties(vO, bean);
        Product product = productRepository.findById(vO.getIdProduct()).orElseThrow(() -> new NoSuchElementException());
        GroupTable groupTable = groupTableRepository.findById(vO.getIdGroupTable()).orElseThrow(() -> new NoSuchElementException());
        detailProducts = product.getDetailProducts();
        HashMap<String, Object> checkMap = getMessageSave(detailProducts, vO.getQuantity());
        if ((boolean) checkMap.get("check")) {
            bean.setProduct(product);
            bean.setGroupTable(groupTable);
            bean.setStatus(1);
            bean.setTimeOrder(Instant.now());
            String nameProduct = product.getProductName();
            bean = ordercfRepository.save(bean);
            updateIngredientSave(detailProducts, vO.getQuantity(),nameProduct);
            map.put("check", true);
            map.put("value", toDTO(bean));
            return map;
        } else {
            map.put("check", false);
            map.put("message", checkMap.get("message"));
            return map;
        }
    }

    public HashMap<String, Object> update(String id, OrdercfUpdateVO vO) {
        HashMap<String, Object> map = new HashMap<>();
        Ordercf ordercf = requireOne(id);
        if (ordercf.getStatus() == 1 && vO.getStatus() == 1) {
            if (ordercf != null) {
                Product product = ordercf.getProduct();
                HashMap<String, Object> checkMap = getMessageUpdate(product.getDetailProducts(), ordercf.getQuantity(), vO.getQuantity());
                if ((boolean) checkMap.get("check")) {
                    updateIngredientUpdate(product.getDetailProducts(), ordercf.getQuantity(), vO.getQuantity(), product.getProductName());
                    System.out.println(vO.getQuantity());
                    System.out.println(ordercf.getQuantity());
                    BeanUtils.copyProperties(vO, ordercf);
                    ordercf = ordercfRepository.save(ordercf);
                    map.put("check", true);
                    map.put("value", toDTO(ordercf));
                    return map;
                } else {
                    map.put("check", false);
                    map.put("message", checkMap.get("message"));
                    return map;
                }
            } else {
                map.put("check", false);
                map.put("message", "Không tìm thấy đơn hàng");
                return map;
            }
        }
        else if (ordercf.getStatus() == 2){
            map.put("check", false);
            map.put("message", "Đơn hàng đã được xử lý");
            return map;
        }
        else if (ordercf.getStatus() == 3){
            map.put("check", false);
            map.put("message", "Đơn hàng đã được hủy");
            return map;
        }
        if (vO.getStatus() == 3){
            Product product = ordercf.getProduct();
            ordercf.setStatus(3);
            updateIngredientUpdate(ordercf.getProduct().getDetailProducts(), ordercf.getQuantity(), 0,product.getProductName());
            ordercfRepository.save(ordercf);
            map.put("check", true);
            map.put("value", toDTO(ordercf));
            return map;
        }
        else {
            map.put("check", false);
            map.put("message", "Đơn hàng đã được xử lý");
            return map;
        }
    }
    public HashMap<String, Object> update_final(String id, OrdercfUpdateVO vo){
        HashMap<String, Object> map = new HashMap<>();
        Ordercf ordercf = requireOne(id);
        if (ordercf != null) {
            ordercf.setStatus(2);
            ordercf = ordercfRepository.save(ordercf);
            map.put("check", true);
            map.put("value", toDTO(ordercf));
            return map;
        } else {
            map.put("check", false);
            map.put("message", "Không tìm thấy đơn hàng");
            return map;
        }
    }

    public HashMap<String, Object> getMessageSave(Set<DetailProduct> detailProducts, int quantityProduct) {
        HashMap<String, Object> map = new HashMap<>();
        boolean check = true;
        String message = "Số lượng nguyên liệu ";
        for (DetailProduct detailProduct : detailProducts) {
            Ingredient ingredient = detailProduct.getIngredient();
            if (ingredient.getQuantity() < detailProduct.getQuantity() * quantityProduct) {
                float quantityHave = (float) (detailProduct.getQuantity() * quantityProduct - ingredient.getQuantity());
                check = false;
                message += ingredient.getIngredientName() + " cần thêm " + quantityHave + " " + ingredient.getUnit() + ", ";
            }
        }
        map.put("check", check);
        map.put("message", message);
        return map;
    }
    public HashMap<String, Object> getMessageUpdate(Set<DetailProduct> detailProducts, int quantityOld, int quantityNew) {
        HashMap<String, Object> map = new HashMap<>();
        boolean check = true;
        String message = "Số lượng nguyên liệu ";
        float quantity = quantityNew - quantityOld;
        for (DetailProduct detailProduct : detailProducts) {
            Ingredient ingredient = detailProduct.getIngredient();
            if (ingredient.getQuantity() < detailProduct.getQuantity() * quantity) {
                float quantityHave = (float) (detailProduct.getQuantity() * quantity - ingredient.getQuantity());
                check = false;
                message += ingredient.getIngredientName() + " cần thêm " + quantityHave + " " + ingredient.getUnit() + ", ";
            }
        }
        map.put("check", check);
        map.put("message", message);
        return map;
    }

    public HashMap<String, Object> updateIngredientUpdate(Set<DetailProduct> detailProducts, int quantityOld, int quantityNew, String nameProduct) {
        HashMap<String, Object> map = new HashMap<>();
        int quantity = quantityNew - quantityOld;
        System.out.println(quantity);
        for (DetailProduct detailProduct : detailProducts) {
            Ingredient ingredient = detailProduct.getIngredient();
            Double currentQuantity = ingredient.getQuantity();
            ingredient.setQuantity(ingredient.getQuantity() - quantity * detailProduct.getQuantity());
            Double newQuantity = ingredient.getQuantity();
            ingredientRepository.save(ingredient);
            save(ingredient.getId(),"Cập nhật Order sản phẩm " + nameProduct + " Số lượng "+ quantity, newQuantity - currentQuantity);
        }
        map.put("check", true);
        return map;
    }

    public void updateIngredientSave(Set<DetailProduct> detailProducts, int quantity, String nameProduct) {
        for (DetailProduct detailProduct : detailProducts) {
            Ingredient ingredient = detailProduct.getIngredient();
            Double currentQuantity = ingredient.getQuantity();
            System.out.println(ingredient.getQuantity());
            System.out.println(detailProduct.getQuantity());
            ingredient.setQuantity(ingredient.getQuantity() - detailProduct.getQuantity() * quantity);
            Double newQuantity = ingredient.getQuantity();
            ingredientRepository.save(ingredient);
            save(ingredient.getId(),"Order " + nameProduct + " số lượng " +quantity , newQuantity - currentQuantity);
        }
    }

    public  HashMap<String, Object> delete(String id) {
        HashMap<String, Object> map = new HashMap<>();
        Ordercf ordercf = requireOne(id);
        if (ordercf != null) {
            Product product = ordercf.getProduct();
            if (ordercf.getStatus() == 1) {
                updateIngredientUpdate(ordercf.getProduct().getDetailProducts(), ordercf.getQuantity(), 0, product.getProductName());
                map.put("check", true);
                return map;
            } else  {
               ordercfRepository.delete(ordercf);
                map.put("check", true);
                return map;
            }
        } else {
            map.put("check", false);
            map.put("message", "Không tìm thấy đơn hàng");
            return map;
        }
    }

    public OrdercfDTO getById(String id) {
        Ordercf original = requireOne(id);
        return toDTO(original);
    }

    public OrdercfNoMapPing getByIdNoMapPing(String id) {
        OrdercfNoMapPing original = ordercfNoMapPingRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return original;
    }

    public Page<OrdercfDTO> query(OrdercfQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private OrdercfDTO toDTO(Ordercf original) {
        OrdercfDTO bean = new OrdercfDTO();
        BeanUtils.copyProperties(original, bean);
        bean.setIdGroupTable(original.getGroupTable().getId());
        bean.setIdProduct(original.getProduct().getId());
        return bean;
    }

    private Ordercf requireOne(String id) {
        return ordercfRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<Ordercf> findAll() {
        return ordercfRepository.findAll();
    }

    public List<OrdercfNoMapPing> findByGroupTableId(String id) {
        return ordercfNoMapPingRepository.findByGroupTableId(id);
    }

    public ProductNoMapPing findByProductId(String id) {
        return productNoMapPingRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    //findByGroupTableId(String groupTableId);
    public GroupTableNoMapPing findGroupTableId(String id) {
        return groupTableNoMapPingRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public List<OrdercfNoMapPing> findAllNoMapp() {
        return ordercfNoMapPingRepository.findAll(Sort.by(Sort.Direction.ASC, "timeOrder"));
    }

    public String save(String idIngredient, String content, Double valueChange) {
        DetailIngredient bean = new DetailIngredient();
        bean.setValueChange(valueChange);
        bean.setIdIngredient(ingredientRepository.findById(idIngredient).orElse(null));
        bean.setDateTime(Instant.now());
        bean.setContent(content);
        bean = detailIngredientRepository.save(bean);
        return bean.getId();
    }

}