package com.hegaro.medicalapp.service;

import java.util.List;

public interface CrudGeneric<T>{
    T register(T t);
    T update(T t);
    List<T> findAll();
    T findById(Integer id);
    void delete(Integer id);
}