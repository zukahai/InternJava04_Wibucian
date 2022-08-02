package com.java04.wibucian.services;

import com.java04.wibucian.interfaces.ServiceInterface;
import com.java04.wibucian.models.Category;
import com.java04.wibucian.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ServiceInterface<Category> {

    public void test() {
        System.out.println("service ok");
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).get()    ;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateById(Category category) {
        Category categoryFind = this.findById(category.getId());
        if (categoryFind != null) {
            categoryFind.setName(category.getName());
            categoryRepository.save(categoryFind);
        }
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
