package com.ozhegov.tennis.dao;

import java.util.List;

public interface DAO<T> {
    void create(T t);
    void delete(T t);
    T get();
    List<T> getAll();
}
