package ru.job4j.dream.dataBase;

import ru.job4j.dream.model.Post;

import java.util.List;

public interface DAO<T> {
    void create(T vacancy);
    T read(int id);
    void update(T vacancy);
    void delete(T vacancy);
    List<T> findAll();
}
