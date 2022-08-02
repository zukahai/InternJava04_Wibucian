package com.java04.wibucian.services;

import com.java04.wibucian.interfaces.ServiceInterface;
import com.java04.wibucian.models.Category;
import com.java04.wibucian.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ServiceInterface<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void updateById(Category category) {

    }

    @Override
    public ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<>();
        categories = (ArrayList<Category>) categoryRepository.findAll();
        return categories;
    }

    @Override
    public ArrayList<Category> search(String keyword) {
        return null;
    }
}
