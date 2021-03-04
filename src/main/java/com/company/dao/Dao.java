package com.company.dao;

import java.util.List;

public interface Dao <Entity, Key>{
    void save(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
    Entity readById(Key id);
    List<Entity> readAll();
}
