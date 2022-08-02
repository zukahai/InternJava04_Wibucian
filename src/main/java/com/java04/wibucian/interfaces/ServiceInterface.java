package com.java04.wibucian.interfaces;

import java.util.ArrayList;

public interface ServiceInterface<T> {

    public T findById(int id);

    public void save(T t);

    public void deleteById(int id);

    public void updateById(T t);

    public ArrayList<T> findAll();

    public ArrayList<T> search(String keyword);

}
